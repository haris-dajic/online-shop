package com.OnlineMarket.AdminService.Communication;

import com.OnlineMarket.AdminService.ActiveParameters;
import com.OnlineMarket.AdminService.Models.ProductAmount;
import com.OnlineMarket.AdminService.Repositories.ProductRepository;
import com.OnlineMarket.AdminService.Repositories.ProductAmountRepository;
import com.google.gson.Gson;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;

public class AdminServiceSubscriber {

    @Autowired
    ProductRepository productRepository;
    @Autowired
    ProductAmountRepository productAmountRepository;
    @Autowired
    AdminServicePublisher adminServicePublisher;
    @Autowired
    ActiveParameters activeParameters;

    @RabbitListener(queues = "ReceiveActiveUserIDQueueAdmin")
    public void receiveActiveUserID(String message) {
        activeParameters.setActiveUserID(Integer.parseInt(message));
    }

    @RabbitListener(queues = "ReceiveActiveUserNameAndSurnameQueueAdmin")
    public void receiveActiveUserNameAndSurname(String message) {
        activeParameters.setActiveNameAndSurname(message);
    }

    @RabbitListener(queues = "ReceiveActiveUserRoleQueueAdmin")
    public void receiveActiveUserRole(String message) {
        activeParameters.setActiveRole(message);
    }

    @RabbitListener(queues = "ReceiveProductAmountUpdatedQueueAdmin")
    public void receiveProductAmountUpdated(String message) throws Exception {
        ProductAmount updatedProductAmount = new Gson().fromJson(message, ProductAmount.class);
        productAmountRepository.save(updatedProductAmount);
    }
}
