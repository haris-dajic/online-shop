package com.onlineMarket.ShoppingCartService.Communication;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ShoppingCartServiceSubscriberConfiguration {

    @Bean
    public DirectExchange registrationServiceExchange() { return new DirectExchange("RegistrationServiceDirectExchange"); }
    @Bean
    public DirectExchange adminServiceExchange() {
        return new DirectExchange("AdminServiceDirectExchange");
    }
    @Bean
    public DirectExchange marketInfoServiceExchange() {
        return new DirectExchange("MarketInfoServiceDirectExchange");
    }

    @Bean
    public Queue receiveUserCreatedOrUpdatedQueue() { return new Queue("ReceiveUserCreatedOrUpdatedQueueShoppingCart"); }
    @Bean
    public Binding receiveUserCreatedOrUpdatedBinding(Queue receiveUserCreatedOrUpdatedQueue, DirectExchange registrationServiceExchange)
    {
        return BindingBuilder
                .bind(receiveUserCreatedOrUpdatedQueue)
                .to(registrationServiceExchange)
                .with("user.created");
    }

    @Bean
    public Queue receiveUserDeletedQueue() { return new Queue("ReceiveUserDeletedQueueShoppingCart"); }
    @Bean
    public Binding receiveUserDeletedBinding(Queue receiveUserDeletedQueue, DirectExchange registrationServiceExchange)
    {
        return BindingBuilder
                .bind(receiveUserDeletedQueue)
                .to(registrationServiceExchange)
                .with("user.deleted");
    }

    @Bean
    public Queue receiveUserIDQueue() { return new Queue("ReceiveUserIDQueueShoppingCart"); }
    @Bean
    public Binding receiveUserIDBinding(Queue receiveUserIDQueue, DirectExchange registrationServiceExchange) {
        return BindingBuilder
                .bind(receiveUserIDQueue)
                .to(registrationServiceExchange)
                .with("user.id");
    }

    @Bean
    public Queue receiveProductCreatedOrUpdatedQueue() { return new Queue("ReceiveProductCreatedOrUpdatedQueueShoppingCart"); }
    @Bean
    public Binding receiveProductCreatedBinding(Queue receiveProductCreatedOrUpdatedQueue, DirectExchange adminServiceExchange) {
        return BindingBuilder
                .bind(receiveProductCreatedOrUpdatedQueue)
                .to(adminServiceExchange)
                .with("product.created");
    }

    @Bean
    public Queue receiveProductDeletedQueue() { return new Queue("ReceiveProductDeletedQueueShoppingCart"); }
    @Bean
    public Binding receiveProductDeletedBinding(Queue receiveProductDeletedQueue, DirectExchange adminServiceExchange) {
        return BindingBuilder
                .bind(receiveProductDeletedQueue)
                .to(adminServiceExchange)
                .with("product.deleted");
    }

    @Bean
    public Queue receiveProductSentQueue() { return new Queue("ReceiveProductSentQueueShoppingCart"); }
    @Bean
    public Binding receiveProductSentBinding(Queue receiveProductSentQueue, DirectExchange marketInfoServiceExchange){
        return BindingBuilder
                .bind(receiveProductSentQueue)
                .to(marketInfoServiceExchange)
                .with("product.sent");
    }

    @Bean
    public Queue receiveMarketCreatedOrUpdatedQueue() { return new Queue("ReceiveMarketCreatedOrUpdatedQueueShoppingCart"); }
    @Bean
    public Binding receiveMarketCreatedBinding(Queue receiveMarketCreatedOrUpdatedQueue, DirectExchange adminServiceExchange) {
        return BindingBuilder
                .bind(receiveMarketCreatedOrUpdatedQueue)
                .to(adminServiceExchange)
                .with("market.created");
    }

    @Bean
    public Queue receiveMarketDeletedQueue() { return new Queue("ReceiveMarketDeletedQueueShoppingCart"); }
    @Bean
    public Binding receiveMarketDeletedBinding(Queue receiveMarketDeletedQueue, DirectExchange adminServiceExchange) {
        return BindingBuilder
                .bind(receiveMarketDeletedQueue)
                .to(adminServiceExchange)
                .with("market.deleted");
    }

    @Bean
    public Queue receiveMarketIDQueue() { return new Queue("ReceiveMarketIDQueueShoppingCart"); }
    @Bean
    public Binding receiveMarketIDBinding(Queue receiveMarketIDQueue, DirectExchange marketInfoServiceExchange) {
        return BindingBuilder
                .bind(receiveMarketIDQueue)
                .to(marketInfoServiceExchange)
                .with("market.id");
    }

    @Bean
    public ShoppingCartServiceSubscriber shoppingCartServiceSubscriber() {
        return new ShoppingCartServiceSubscriber();
    }
}
