package com.OnlineMarket.AdminService.Communication;

import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AdminServicePublisherConfiguration {

    @Bean
    public DirectExchange senderDirectExchange() {
        return new DirectExchange("AdminServiceDirectExchange");
    }

    @Bean
    public AdminServicePublisher adminServicePublisher(RabbitTemplate rabbitTemplate, DirectExchange senderDirectExchange) {
        return new AdminServicePublisher(rabbitTemplate, senderDirectExchange);
    }
}
