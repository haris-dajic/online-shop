package com.onlineMarket.MarketInfoService.Communication;


import com.google.gson.Gson;
import com.onlineMarket.MarketInfoService.ActiveParameters;
import com.onlineMarket.MarketInfoService.Models.Market;
import com.onlineMarket.MarketInfoService.Models.Product;
import com.onlineMarket.MarketInfoService.Models.ProductAmount;
import com.onlineMarket.MarketInfoService.Repositories.MarketRepository;
import com.onlineMarket.MarketInfoService.Repositories.ProductAmountRepository;
import com.onlineMarket.MarketInfoService.Repositories.ProductRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

public class MarketInfoServiceSubscriber {

    @Autowired
    ActiveParameters activeParameters;
    
    @Autowired
    ProductRepository productRepository;
    
    @Autowired
    MarketRepository marketRepository;
    
    @Autowired
    ProductAmountRepository productAmountRepository;

    @RabbitListener(queues = "ReceiveUserNameAndSurnameQueueMarketInfo")
    public void receiveUserNameAndSurname(String message) {
        activeParameters.setActiveNameAndSurname(message);
    }

    @RabbitListener(queues = "ReceiveUserRoleQueueMarketInfo")
    public void receiveUserRole(String message) {
        activeParameters.setActiveRole(message);
    }

    @RabbitListener(queues = "ReceiveUserIDQueueMarketInfo")
    public void receiveUserIDQueue(String message) {
        activeParameters.setActiveUserID(Integer.parseInt(message));
    }

    @RabbitListener(queues = "ReceiveProductCreatedOrUpdatedQueueMarketInfo")
    public void receiveProductCreatedOrUpdated(String message) {
        Map<String, String> data = stringToMap(message);
        productRepository.save(getProductData(data));
    }

    @RabbitListener(queues = "ReceiveProductDeletedQueueMarketInfo")
    public void receiveProductDeleted(String message) {
        Integer product_id = Integer.parseInt(message);
        
        productRepository.deleteById(product_id);
        productAmountRepository.deleteAllByProduct_Id(product_id);
    }

    @RabbitListener(queues = "ReceiveMarketCreatedOrUpdatedQueueMarketInfo")
    public void receiveMarketCreatedOrUpdated(String message) {
        Market newMarket = new Gson().fromJson(message, Market.class);
        marketRepository.save(newMarket);
    }

    @RabbitListener(queues = "ReceiveMarketDeletedQueueMarketInfo")
    public void receiveMarketDeleted(String message) {
        Integer market_id = Integer.parseInt(message);

        marketRepository.deleteById(market_id);
        productAmountRepository.deleteAllByMarket_Id(market_id);
    }

    @RabbitListener(queues = "ReceiveProductAmountCreatedOrUpdatedQueueMarketInfo")
    public void receiveProductAmountCreatedOrUpdated(String message) {
        Map<String, String> data = stringToMap(message);
        Integer id = Integer.parseInt(data.get("product_amount_id"));
        Integer product_id = Integer.parseInt(data.get("product_id"));
        Integer market_id = Integer.parseInt(data.get("market_id"));
        Integer amount = Integer.parseInt(data.get("amount"));

        Market market = marketRepository.findById(market_id).get();
        Product product = productRepository.findById(product_id).get();

        ProductAmount newProductAmount = new ProductAmount(id, amount, product, market);
        productAmountRepository.save(newProductAmount);
    }

    @RabbitListener(queues = "ReceiveProductAmountUpdatedQueue")
    public void receiveProductAmountUpdated(String message) throws Exception {
        Map<String, String> data = stringToMap(message);
        Integer product_id = Integer.parseInt(data.get("id"));
        activeParameters.increaseProductAmountByOne(product_id);
    }

    @RabbitListener(queues = "ReceiveProductRemovedQueueMarketInfo")
    public void receiveProductRemoved(String message) throws Exception{
        Map<String, String> data = stringToMap(message);
        Integer product_id = Integer.parseInt(data.get("id"));
        Integer amount = Integer.parseInt(data.get("amount"));
        System.out.println(activeParameters.getProductAmounts().toString());
        activeParameters.increaseProductAmountByNumber(product_id, amount);
        System.out.println(activeParameters.getProductAmounts().toString());
    }

    public Product getProductData(Map<String, String> data){
        String name = data.get("name");
        String category = data.get("category");
        double price = Double.parseDouble(data.get("price"));
        double discount = Double.parseDouble(data.get("discount"));
        String barcode = data.get("barcode");
        String description = data.get("description");
        String imageURL = data.get("imageURL");
        Integer product_id = Integer.parseInt(data.get("product_id"));

        return new Product(product_id, name, category, price, discount, barcode, description, imageURL);
    }

    public static Map<String, String> stringToMap(String input) {
        Map<String, String> map = new HashMap<String, String>();

        String[] nameValuePairs = input.split("&");
        for (String nameValuePair : nameValuePairs) {
            String[] nameValue = nameValuePair.split("=");
            try {
                map.put(URLDecoder.decode(nameValue[0], "UTF-8"), nameValue.length > 1 ? URLDecoder.decode(
                        nameValue[1], "UTF-8") : "");
            } catch (UnsupportedEncodingException e) {
                throw new RuntimeException("This method requires UTF-8 encoding support", e);
            }
        }
        return map;
    }
}
