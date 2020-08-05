package com.onlineMarket.ShoppingCartService.Communication;

import com.google.gson.Gson;
import com.onlineMarket.ShoppingCartService.Models.ActiveParameters;
import com.onlineMarket.ShoppingCartService.Models.Market;
import com.onlineMarket.ShoppingCartService.Models.User;
import com.onlineMarket.ShoppingCartService.Models.Product;
import com.onlineMarket.ShoppingCartService.Repositories.MarketRepository;
import com.onlineMarket.ShoppingCartService.Repositories.UserRepository;
import com.onlineMarket.ShoppingCartService.Repositories.ProductRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

public class ShoppingCartServiceSubscriber {

    @Autowired
    UserRepository userRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    MarketRepository marketRepository;

    @Autowired
    ActiveParameters activeParameters;

    @RabbitListener(queues = "ReceiveUserCreatedOrUpdatedQueueShoppingCart")
    public void receiveUserCreatedOrUpdated(String message) {
        User user = new Gson().fromJson(message, User.class);
        userRepository.save(user);
    }

    @RabbitListener(queues = "ReceiveUserDeletedQueueShoppingCart")
    public void receiveUserDeleted(String message) {
        Integer user_id = Integer.parseInt(message);
        userRepository.deleteById(user_id);
    }

    @RabbitListener(queues = "ReceiveUserIDQueueShoppingCart")
    public void receiveUserID(String message) {
        int activeUserID = Integer.parseInt(message);
        activeParameters.setActiveUserID(activeUserID);
    }

    @RabbitListener(queues = "ReceiveProductCreatedOrUpdatedQueueShoppingCart")
    public void receiveProductCreatedOrUpdated(String message) {
        Map<String, String> data = stringToMap(message);
        productRepository.save(getProductData(data));
    }

    @RabbitListener(queues = "ReceiveProductDeletedQueueShoppingCart")
    public void receiveProductDeleted(String message) {
        Integer product_id = Integer.parseInt(message);
        productRepository.deleteById(product_id);
    }

    @RabbitListener(queues = "ReceiveProductSentQueueShoppingCart")
    public void receiveProductSent(String message) {
        Map<String, String> data = stringToMap(message);
        Integer product_id = Integer.parseInt(data.get("id"));
        Integer amount = Integer.parseInt(data.get("amount"));
        for(Product product: activeParameters.getActiveProductList()) {
            if(product.getId() == product_id) {
                product.addAmount(amount);
                return;
            }
        }

        Product product = productRepository.findById(product_id).get();
        product.setAmount(amount);
        activeParameters.addProduct(product);
    }

    @RabbitListener(queues = "ReceiveMarketCreatedOrUpdatedQueueShoppingCart")
    public void receiveMarketCreatedOrUpdated(String message) {
        Market newMarket = new Gson().fromJson(message, Market.class);
        marketRepository.save(newMarket);
    }

    @RabbitListener(queues = "ReceiveMarketDeletedQueueShoppingCart")
    public void receiveMarketDeleted(String message) {
        Integer market_id = Integer.parseInt(message);
        marketRepository.deleteById(market_id);
    }

    @RabbitListener(queues = "ReceiveMarketIDQueueShoppingCart")
    public void receiveMarketID(String message) {
        int market_id = Integer.parseInt(message);
        activeParameters.setActiveMarketID(market_id);
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
        System.out.println(data.get("product_id"));

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
