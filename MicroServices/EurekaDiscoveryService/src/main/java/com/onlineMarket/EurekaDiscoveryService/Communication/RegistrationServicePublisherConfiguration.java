package com.onlineMarket.EurekaDiscoveryService.Communication;

import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RegistrationServicePublisherConfiguration {
    @Bean
    public DirectExchange senderDirectExchange() {
        return new DirectExchange("RegistrationServiceDirectExchange");
    }

    @Bean
    public RegistrationServicePublisher registrationServicePublisher(RabbitTemplate rabbitTemplate, DirectExchange senderDirectExchange) {
        return new RegistrationServicePublisher(rabbitTemplate, senderDirectExchange);
    }
}
