package com.github.diogocerqueiralima.emailservice.consumers;

import com.github.diogocerqueiralima.emailservice.events.CreateCommentEvent;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class NotificationConsumer {

    @RabbitListener(queues = "#{emailQueue.name}")
    public void onCreateCommentEvent(CreateCommentEvent comment) {
        System.out.println(comment.toString());
    }

}
