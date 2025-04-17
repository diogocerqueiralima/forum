package com.github.diogocerqueiralima.emailservice.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.amqp.core.*;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Value("${broker.exchange.notification}")
    private String exchange;

    @Bean
    public FanoutExchange notificationExchange() {
        return new FanoutExchange(exchange);
    }

    @Bean
    public Queue emailQueue() {
        return new AnonymousQueue();
    }

    @Bean
    public Binding bindingEmail(Queue queue, FanoutExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange);
    }

    @Bean
    public Jackson2JsonMessageConverter messageConverter() {

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());

        return new Jackson2JsonMessageConverter(objectMapper);
    }

}
