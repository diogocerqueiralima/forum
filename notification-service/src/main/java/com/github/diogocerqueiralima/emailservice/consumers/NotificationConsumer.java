package com.github.diogocerqueiralima.emailservice.consumers;

import com.github.diogocerqueiralima.emailservice.events.CreateCommentEvent;
import com.github.diogocerqueiralima.emailservice.services.NotificationService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class NotificationConsumer {

    private final NotificationService notificationService;

    public NotificationConsumer(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @RabbitListener(queues = "#{emailQueue.name}")
    public void onCreateCommentEvent(CreateCommentEvent event) {
        notificationService.notifyTopicOwner(event);
    }

}


