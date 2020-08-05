package com.onlineMarket.MarketInfoService.Communication;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MarketInfoServiceSubscriberConfiguration {
    
    @Bean DirectExchange registrationServiceExchange() { return new DirectExchange("RegistrationServiceDirectExchange"); }
    @Bean DirectExchange adminServiceExchange() { return new DirectExchange("AdminServiceDirectExchange"); }
    @Bean DirectExchange shoppingCartServiceExchange() { return new DirectExchange("ShoppingCartServiceDirectExchange"); }

    @Bean
    public Queue receiveUserNameAndSurnameQueue() { return new Queue("ReceiveUserNameAndSurnameQueueMarketInfo"); }
    @Bean
    public Binding receiveUserNameAndSurnameBinding(Queue receiveUserNameAndSurnameQueue, DirectExchange registrationServiceExchange) {
        return BindingBuilder
                .bind(receiveUserNameAndSurnameQueue)
                .to(registrationServiceExchange)
                .with("user.name");
    }

    @Bean
    public Queue receiveUserRoleQueue() { return new Queue("ReceiveUserRoleQueueMarketInfo"); }
    @Bean
    public Binding receiveUserRoleBinding(Queue receiveUserRoleQueue, DirectExchange registrationServiceExchange) {
        return BindingBuilder
                .bind(receiveUserRoleQueue)
                .to(registrationServiceExchange)
                .with("user.role");
    }

    @Bean
    public Queue receiveUserIDQueue() { return new Queue("ReceiveUserIDQueueMarketInfo"); }
    @Bean
    public Binding receiveUserIDBinding(Queue receiveUserIDQueue, DirectExchange registrationServiceExchange) {
        return BindingBuilder
                .bind(receiveUserIDQueue)
                .to(registrationServiceExchange)
                .with("user.id");
    }

    @Bean
    public Queue receiveProductCreatedOrUpdatedQueue() { return new Queue("ReceiveProductCreatedOrUpdatedQueueMarketInfo"); }
    @Bean
    public Binding receiveProductCreatedBinding(Queue receiveProductCreatedOrUpdatedQueue, DirectExchange adminServiceExchange) {
        return BindingBuilder
                .bind(receiveProductCreatedOrUpdatedQueue)
                .to(adminServiceExchange)
                .with("product.created");
    }

    @Bean
    public Queue receiveProductDeletedQueue() { return new Queue("ReceiveProductDeletedQueueMarketInfo"); }
    @Bean
    public Binding receiveProductDeletedBinding(Queue receiveProductDeletedQueue, DirectExchange adminServiceExchange) {
        return BindingBuilder
                .bind(receiveProductDeletedQueue)
                .to(adminServiceExchange)
                .with("product.deleted");
    }

    @Bean
    public Queue receiveMarketCreatedOrUpdatedQueue() { return new Queue("ReceiveMarketCreatedOrUpdatedQueueMarketInfo"); }
    @Bean
    public Binding receiveMarketCreatedBinding(Queue receiveMarketCreatedOrUpdatedQueue, DirectExchange adminServiceExchange) {
        return BindingBuilder
                .bind(receiveMarketCreatedOrUpdatedQueue)
                .to(adminServiceExchange)
                .with("market.created");
    }

    @Bean
    public Queue receiveMarketDeletedQueue() { return new Queue("ReceiveMarketDeletedQueueMarketInfo"); }
    @Bean
    public Binding receiveMarketDeletedBinding(Queue receiveMarketDeletedQueue, DirectExchange adminServiceExchange) {
        return BindingBuilder
                .bind(receiveMarketDeletedQueue)
                .to(adminServiceExchange)
                .with("market.deleted");
    }

    @Bean
    public Queue receiveProductAmountCreatedOrUpdatedQueue() { return new Queue("ReceiveProductAmountCreatedOrUpdatedQueueMarketInfo"); }
    @Bean
    public Binding receiveProductAmountCreatedOrUpdatedBinding(Queue receiveProductAmountCreatedOrUpdatedQueue, DirectExchange adminServiceExchange) {
        return BindingBuilder
                .bind(receiveProductAmountCreatedOrUpdatedQueue)
                .to(adminServiceExchange)
                .with("productamount.created");
    }

    @Bean
    public Queue receiveProductAmountUpdatedQueue() { return new Queue("ReceiveProductAmountUpdatedQueue"); }
    @Bean
    public Binding receiveProductAmountUpdatedBinding(Queue receiveProductAmountUpdatedQueue, DirectExchange shoppingCartServiceExchange) {
        return BindingBuilder
                .bind(receiveProductAmountUpdatedQueue)
                .to(shoppingCartServiceExchange)
                .with("productamount.updated");
    }

    @Bean
    public Queue receiveProductRemovedQueue() { return new Queue("ReceiveProductRemovedQueueMarketInfo"); }
    @Bean
    public Binding receiveProductRemovedBinding(Queue receiveProductRemovedQueue, DirectExchange shoppingCartServiceExchange) {
        return BindingBuilder
                .bind(receiveProductRemovedQueue)
                .to(shoppingCartServiceExchange)
                .with("product.removed");
    }

    @Bean
    public MarketInfoServiceSubscriber marketInfoServiceSubscriber() {
        return new MarketInfoServiceSubscriber();
    }
}
