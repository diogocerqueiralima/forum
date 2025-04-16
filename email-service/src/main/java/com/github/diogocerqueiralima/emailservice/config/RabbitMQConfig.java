package com.github.diogocerqueiralima.emailservice.config;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Value("${broker.queue.email}")
    private String queueEmail;

    @Bean
    public Queue emailQueue() {
        return new Queue(queueEmail, true);
    }

}
