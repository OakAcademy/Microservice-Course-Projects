package uk.oakacademy.emailservice.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uk.oakacademy.emailservice.entities.StudentEmail;

@RestController
@RequestMapping("/emails")
@RequiredArgsConstructor
public class EmailController {
    private final JavaMailSender javaMailSender;
@PostMapping
    public ResponseEntity<StudentEmail> sendMail(@RequestBody StudentEmail studentEmail) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(studentEmail.getEmail());
        simpleMailMessage.setSubject("Mail control");
        simpleMailMessage.setText("This Email is first email from email service");
//        simpleMailMessage.setCc();
        javaMailSender.send(simpleMailMessage);
        return ResponseEntity.ok(studentEmail);
    }


}
