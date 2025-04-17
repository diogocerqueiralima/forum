package com.github.diogocerqueiralima.forumservice.producers;

import com.github.diogocerqueiralima.forumservice.events.CreateCommentEvent;
import com.github.diogocerqueiralima.forumservice.models.Comment;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class NotificationProducer {

    private final RabbitTemplate rabbitTemplate;

    @Value("${broker.exchange.notification}")
    private String exchange;

    public NotificationProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void send(Comment comment) {

        CreateCommentEvent event = comment.toCreateCommentEvent();

        rabbitTemplate.convertAndSend(exchange, "", event);
    }

}
