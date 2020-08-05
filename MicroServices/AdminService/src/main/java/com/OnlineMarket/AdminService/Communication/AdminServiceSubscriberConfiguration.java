package com.OnlineMarket.AdminService.Communication;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AdminServiceSubscriberConfiguration {

    @Bean
    public DirectExchange marketInfoServiceExchange() {
        return new DirectExchange("MarketInfoServiceDirectExchange");
    }
    @Bean DirectExchange registrationServiceExchange() { return new DirectExchange("RegistrationServiceDirectExchange"); }

    @Bean
    public Queue receiveMarketIDQueue() {
        return new Queue("ReceiveMarketIDQueueAdmin");
    }
    @Bean
    public Binding receiveMarketIDBinding(Queue receiveMarketIDQueue, DirectExchange marketInfoServiceExchange){
        return BindingBuilder
                .bind(receiveMarketIDQueue)
                .to(marketInfoServiceExchange)
                .with("market.id");
    }

    @Bean
    public Queue receiveActiveUserIDQueue() {
        return new Queue("ReceiveActiveUserIDQueueAdmin");
    }
    @Bean
    public Binding receiveActiveUserIDBinding(Queue receiveActiveUserIDQueue, DirectExchange registrationServiceExchange) {
        return BindingBuilder
                .bind(receiveActiveUserIDQueue)
                .to(registrationServiceExchange)
                .with("user.id");
    }

    @Bean
    public Queue receiveActiveUserNameAndSurnameQueue() { return new Queue("ReceiveActiveUserNameAndSurnameQueueAdmin"); }
    @Bean
    public Binding receiveUserNameAndSurnameBinding(Queue receiveActiveUserNameAndSurnameQueue, DirectExchange registrationServiceExchange) {
        return BindingBuilder
                .bind(receiveActiveUserNameAndSurnameQueue)
                .to(registrationServiceExchange)
                .with("user.name");
    }

    @Bean
    public Queue receiveActiveUserRoleQueue() { return new Queue("ReceiveActiveUserRoleQueueAdmin"); }
    @Bean
    public Binding receiveUserRoleBinding(Queue receiveActiveUserRoleQueue, DirectExchange registrationServiceExchange) {
        return BindingBuilder
                .bind(receiveActiveUserRoleQueue)
                .to(registrationServiceExchange)
                .with("user.role");
    }

    @Bean
    public Queue receiveProductAmountUpdatedQueue() { return new Queue("ReceiveProductAmountUpdatedQueueAdmin"); }
    @Bean
    public Binding receiveProductAmountUpdatedBinding(Queue receiveProductAmountUpdatedQueue, DirectExchange registrationServiceExchange) {
        return BindingBuilder
                .bind(receiveProductAmountUpdatedQueue)
                .to(registrationServiceExchange)
                .with("productamount.updated");
    }

    @Bean
    public AdminServiceSubscriber adminServiceSubscriber() {
        return new AdminServiceSubscriber();
    }
}
