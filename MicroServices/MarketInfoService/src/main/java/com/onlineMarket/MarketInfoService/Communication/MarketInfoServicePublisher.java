package com.onlineMarket.MarketInfoService.Communication;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.onlineMarket.MarketInfoService.Models.Product;
import com.onlineMarket.MarketInfoService.Models.ProductAmount;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;


public class MarketInfoServicePublisher {

    private final RabbitTemplate rabbitTemplate;
    private final DirectExchange directExchange;

    @Autowired
    public MarketInfoServicePublisher(RabbitTemplate rabbitTemplate, @Qualifier("senderDirectExchange") DirectExchange directExchange) {
        this.rabbitTemplate = rabbitTemplate;
        this.directExchange = directExchange;
    }

    public void sendMarketIDMessage(int marketID) {
        rabbitTemplate.convertAndSend(directExchange.getName(), "market.id", Integer.toString(marketID));
    }

    public void sendProductAmountUpdatedMessage(ProductAmount productAmount) {
        rabbitTemplate.convertAndSend(directExchange.getName(), "productamount.updated", serializeProductAmountToJSON(productAmount));
    }

    public void sendProductAmountSentMessage(Integer amount) {
        rabbitTemplate.convertAndSend(directExchange.getName(), "productamount.sent", Integer.toString(amount));
    }

    public void sendProductToShoppingCartMessage(Map<String, String> product) {
        rabbitTemplate.convertAndSend(directExchange.getName(), "product.sent", mapToString(product));
    }

    public String serializeProductToJSON(Product product) {
        ObjectMapper mapper = new ObjectMapper();
        String jsonInString = "";
        try {
            jsonInString = mapper.writeValueAsString(product);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return jsonInString;
    }

    public String serializeProductAmountToJSON(ProductAmount productAmount) {
        ObjectMapper mapper = new ObjectMapper();
        String jsonInString = "";
        try {
            jsonInString = mapper.writeValueAsString(productAmount);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
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