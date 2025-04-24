package com.github.diogocerqueiralima.emailservice.services;

import com.github.diogocerqueiralima.emailservice.dto.UserRepresentation;
import com.github.diogocerqueiralima.emailservice.events.CreateCommentEvent;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class NotificationService {

    private final JavaMailSender mailSender;
    private final RestClient restClient;

    public NotificationService(JavaMailSender mailSender, RestClient restClient) {
        this.mailSender = mailSender;
        this.restClient = restClient;
    }

    /*
        refactor this to using a template engine like thymeleaf
    */
    public void notifyTopicOwner(CreateCommentEvent event) {

        UserRepresentation topicOwner = restClient.get()
                .uri("/users/{id}", event.topicOwnerId())
                .retrieve()
                .body(UserRepresentation.class);

        if (topicOwner == null) {
            Logger.getGlobal().log(Level.SEVERE, "Topic owner not found");
            return;
        }

        UserRepresentation user = restClient.get()
                .uri("/users/{id}", event.userId())
                .retrieve()
                .body(UserRepresentation.class);

        if (user == null) {
            Logger.getGlobal().log(Level.SEVERE, "User not found");
            return;
        }

        String userFullName = user.firstName() + " " + user.lastName();
        String topicOwnerFullName = topicOwner.firstName() + " " + topicOwner.lastName();
        String to = topicOwner.email();
        String subject = String.format("There is a new comment for \"%s\"", event.topicTitle());
        String body = String.format(
                "Hello %s! User %s has created a new comment in your topic \"%s\"",
                topicOwnerFullName, userFullName, event.topicTitle()
        );

        try {
            sendMail(to, subject, body);
        } catch (MessagingException e) {
            Logger.getGlobal().log(Level.SEVERE, e.getMessage());
        }

    }

    private void sendMail(String to, String subject, String body) throws MessagingException {

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(body);

        mailSender.send(message);
    }

}
