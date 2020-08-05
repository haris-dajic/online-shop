package com.onlineMarket.ShoppingCartService.Communication;

import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ShoppingCartServicePublisherConfiguration {

    @Bean
    public DirectExchange shoppingCartSenderDirectExchange() {
        return new DirectExchange("ShoppingCartServiceDirectExchange");
    }

    @Bean
    public ShoppingCartServicePublisher shoppingCartServicePublisher(RabbitTemplate rabbitTemplate, @Qualifier("shoppingCartSenderDirectExchange") DirectExchange senderDirectExchange) {
        return new ShoppingCartServicePublisher(rabbitTemplate, senderDirectExchange);
    }
}
