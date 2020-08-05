package com.onlineMarket.ShoppingCartService.Communication;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.onlineMarket.ShoppingCartService.Models.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;

public class ShoppingCartServicePublisher {

    private Logger logger = LoggerFactory.getLogger(ShoppingCartServicePublisher.class);
    private final RabbitTemplate rabbitTemplate;
    private final DirectExchange directExchange;

    @Autowired
    public ShoppingCartServicePublisher(RabbitTemplate rabbitTemplate, @Qualifier("shoppingCartSenderDirectExchange") DirectExchange directExchange) {
        this.rabbitTemplate = rabbitTemplate;
        this.directExchange = directExchange;
    }

    public void sendOrderCreatedMessage(Order order) {
        rabbitTemplate.convertAndSend(directExchange.getName(), "order.created", serializeNarudzbaToJSON(order));
    }

    public void sendUpdatedProductAmountMessage(Map<String, String> product) {
        rabbitTemplate.convertAndSend(directExchange.getName(), "productamount.updated", mapToString(product));
    }

    public void sendProductRemovedMessage(Map<String, String> product) {
        rabbitTemplate.convertAndSend(directExchange.getName(), "product.removed", mapToString(product));
    }


    public String serializeNarudzbaToJSON(Order order){
        String jsonInString = "";
        try{
            jsonInString = new ObjectMapper().writeValueAsString(order);
        }
        catch (JsonProcessingException e){
            logger.info(String.valueOf(e));
        }

        logger.debug("Serialized message payload: {}", jsonInString);
        return jsonInString;
    }

    public static String mapToString(Map<String, String> map) {
        StringBuilder stringBuilder = new StringBuilder();

        for (String key : map.keySet()) {
            if (stringBuilder.length() > 0) {
                stringBuilder.append("&");
            }
            String value = map.get(key);
            try {
                stringBuilder.append((key != null ? URLEncoder.encode(key, "UTF-8") : ""));
                stringBuilder.append("=");
                stringBuilder.append(value != null ? URLEncoder.encode(value, "UTF-8") : "");
            } catch (UnsupportedEncodingException e) {
                throw new RuntimeException("This method requires UTF-8 encoding support", e);
            }
        }

        return stringBuilder.toString();
    }
}
