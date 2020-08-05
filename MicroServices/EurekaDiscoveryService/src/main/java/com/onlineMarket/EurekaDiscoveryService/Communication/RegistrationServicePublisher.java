package com.onlineMarket.EurekaDiscoveryService.Communication;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.onlineMarket.EurekaDiscoveryService.Models.User;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;

public class RegistrationServicePublisher {

    private final RabbitTemplate rabbitTemplate;
    private final DirectExchange directExchange;

    @Autowired
    public RegistrationServicePublisher(RabbitTemplate rabbitTemplate, DirectExchange directExchange) {
        this.rabbitTemplate = rabbitTemplate;
        this.directExchange = directExchange;
    }

    public void sendUserIDMessage(int userID) {
        rabbitTemplate.convertAndSend(directExchange.getName(), "user.id", Integer.toString(userID));
    }

    public void sendUserNameAndSurnameMessage(String nameAndSurname) {
        rabbitTemplate.convertAndSend(directExchange.getName(), "user.name", nameAndSurname);
    }

    public void sendUserRoleMessage(String role) {
        rabbitTemplate.convertAndSend(directExchange.getName(), "user.role", role);
    }

    public void sendUserCreatedMessage(User user) {
        rabbitTemplate.convertAndSend(directExchange.getName(), "user.created", serializeUserToJSON(user));
    }

    public void sendUserUpdatedMessage(User user) {
        rabbitTemplate.convertAndSend(directExchange.getName(), "user.updated", serializeUserToJSON(user));
    }

    public void sendUserDeletedMessage(Integer user_id) {
        rabbitTemplate.convertAndSend(directExchange.getName(), "user.deleted", Integer.toString(user_id));
    }

    public String serializeUserToJSON(User user){
        String jsonInString = "";
        try{
            jsonInString = new ObjectMapper().writeValueAsString(user);
        }
        catch (JsonProcessingException e){
            e.printStackTrace();
        }
        return jsonInString;
    }
}
