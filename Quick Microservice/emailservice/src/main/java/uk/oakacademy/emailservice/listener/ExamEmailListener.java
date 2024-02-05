package uk.oakacademy.emailservice.listener;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import uk.oakacademy.emailservice.entities.StudentEmail;
@Service
@RequiredArgsConstructor
public class ExamEmailListener {


    private final JavaMailSender javaMailSender;
    @RabbitListener(queues = "quickqueue")
    public void handleMessage(String message) throws JsonProcessingException {
        StudentEmail studentEmail=new ObjectMapper().readValue(message,StudentEmail.class);
        SimpleMailMessage mail=new SimpleMailMessage();
        mail.setTo(studentEmail.getEmail());
        mail.setSubject("Exam score");
        mail.setText("Dear "+ studentEmail.getNameSurname()+" your "+studentEmail.getLesson()+
                " exam score is"+studentEmail.getScore());
        javaMailSender.send(mail);
        System.out.println("message received from exam service");
        System.out.println(studentEmail.toString());
    }
}
