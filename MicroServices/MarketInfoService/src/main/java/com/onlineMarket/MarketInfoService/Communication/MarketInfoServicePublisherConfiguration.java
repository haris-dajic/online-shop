package com.onlineMarket.MarketInfoService.Communication;

import com.onlineMarket.MarketInfoService.Communication.MarketInfoServicePublisher;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MarketInfoServicePublisherConfiguration {
    @Bean
    public DirectExchange senderDirectExchange() {
        return new DirectExchange("MarketInfoServiceDirectExchange");
    }

    @Bean
    public MarketInfoServicePublisher marketInfoServicePublisher(RabbitTemplate rabbitTemplate, DirectExchange senderDirectExchange) {
        return new MarketInfoServicePublisher(rabbitTemplate, senderDirectExchange);
    }
}
