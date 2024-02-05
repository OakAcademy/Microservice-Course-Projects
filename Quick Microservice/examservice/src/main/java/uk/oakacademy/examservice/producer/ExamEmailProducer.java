package uk.oakacademy.examservice.producer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import uk.oakacademy.examservice.entities.StudentEmail;

@Service
@RequiredArgsConstructor
public class ExamEmailProducer {
    @Value("${ms.rabbit.exchange.name}")
    private String exchangeName;
    @Value("${ms.rabbit.routing.name}")
    private String routingName;

    private final AmqpTemplate amqpTemplate;

    public void SendToQueue(StudentEmail studentEmail) throws JsonProcessingException {
        String studentemailString=new ObjectMapper().writeValueAsString(studentEmail);
        System.out.println("Email sent:"+ studentEmail.toString());
        amqpTemplate.convertAndSend(exchangeName,routingName,studentemailString);
    }




}
