package com.losmilos.todoapp.notification.email.service;

import com.losmilos.todoapp.notification.email.Email;
import lombok.AllArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EmailServiceImpl implements EmailService {

    private JavaMailSender javaMailSender;

    public void sendMail(Email email) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();

        simpleMailMessage.setFrom("noreply@todoapp.com");
        simpleMailMessage.setTo(email.getRecipientEmail());
        simpleMailMessage.setText(email.getBody());
        simpleMailMessage.setSubject(email.getSubject());

        javaMailSender.send(simpleMailMessage);
    }

}
