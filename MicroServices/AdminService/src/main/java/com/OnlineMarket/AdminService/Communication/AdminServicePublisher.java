package com.OnlineMarket.AdminService.Communication;

import com.OnlineMarket.AdminService.Models.Market;
import com.OnlineMarket.AdminService.Models.Product;
import com.OnlineMarket.AdminService.Models.ProductAmount;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

public class AdminServicePublisher {
    
    private final RabbitTemplate rabbitTemplate;
    private final DirectExchange directExchange;

    @Autowired
    public AdminServicePublisher(RabbitTemplate rabbitTemplate, DirectExchange directExchange) {
        this.rabbitTemplate = rabbitTemplate;
        this.directExchange = directExchange;
    }

    public void sendProductCreatedOrUpdatedMessage(Map<String, String> product) {
        rabbitTemplate.convertAndSend(directExchange.getName(), "product.created", mapToString(product));
    }
    public void sendProductDeletedMessage(Integer product_id) {
        rabbitTemplate.convertAndSend(directExchange.getName(), "product.deleted", Integer.toString(product_id));
    }

    public void sendMarketCreatedOrUpdatedMessage(Market market) {
        rabbitTemplate.convertAndSend(directExchange.getName(), "market.created", serializeMarketToJSON(market));
    }

    public void sendMarketDeletedMessage(int market_id) {
        rabbitTemplate.convertAndSend(directExchange.getName(), "market.deleted", Integer.toString(market_id));
    }

    public void sendProductAmountCreatedOrUpdatedMessage(Map<String, String> productAmount) {
        rabbitTemplate.convertAndSend(directExchange.getName(), "productamount.created", mapToString(productAmount));
    }

    public String serializeProductToJSON(Product product){
        String jsonInString = "";
        try{
            jsonInString = new ObjectMapper().writeValueAsString(product);
        }
        catch (JsonProcessingException e){
            e.printStackTrace();
        }
        return jsonInString;
    }

    public String serializeMarketToJSON(Market market){
        String jsonInString = "";
        try{
            jsonInString = new ObjectMapper().writeValueAsString(market);
        }
        catch (JsonProcessingException e){
            e.printStackTrace();
        }
        return jsonInString;
    }

    public String serializeProductsToJSON(List<Product> products){
        String jsonInString = "";
        try{
            jsonInString = new ObjectMapper().writeValueAsString(products);
        }
        catch (JsonProcessingException e){
            e.printStackTrace();
        }
        return jsonInString;
    }

    public String serializeProductAmountToJSON(ProductAmount productAmount){
        String jsonInString = "";
        try{
            jsonInString = new ObjectMapper().writeValueAsString(productAmount);
        }
        catch (JsonProcessingException e){
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
