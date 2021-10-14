package com.chatapplicationspringBoot.Service;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailService {
    JavaMailSender javaMailSender;

    public MailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void sendMail(String recipient, String subject, String message) {

        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(recipient, recipient);

        msg.setSubject(subject);
        msg.setText(message);

        javaMailSender.send(msg);
    }

}
