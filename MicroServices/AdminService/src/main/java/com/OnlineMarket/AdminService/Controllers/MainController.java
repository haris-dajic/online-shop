package com.OnlineMarket.AdminService.Controllers;

import com.OnlineMarket.AdminService.ActiveParameters;
import com.OnlineMarket.AdminService.Communication.AdminServicePublisher;
import com.OnlineMarket.AdminService.Models.Market;
import com.OnlineMarket.AdminService.Models.Product;
import com.OnlineMarket.AdminService.Models.ProductAmount;
import com.OnlineMarket.AdminService.Repositories.MarketRepository;
import com.OnlineMarket.AdminService.Repositories.ProductRepository;
import com.OnlineMarket.AdminService.Repositories.ProductAmountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class MainController {

    @Autowired
    ProductRepository productRepository;
    @Autowired
    MarketRepository marketRepository;
    @Autowired
    ProductAmountRepository productAmountRepository;
    @Autowired
    AdminServicePublisher adminServicePublisher;
    @Autowired
    ActiveParameters activeProperties;


    @RequestMapping(method = RequestMethod.GET, value = "user")
    public Map<String, String> getActiveUserInfo() {
        Map<String, String> data = new HashMap<>();
        data.put("username", activeProperties.getActiveNameAndSurname());
        data.put("role", activeProperties.getActiveRole());
        return data;
    }

    @RequestMapping(method = RequestMethod.GET, value = "productamount/all")
    public List<ProductAmount> getAllProductAmounts() throws Exception {
        List<ProductAmount> productAmounts = new ArrayList<>();

        for(ProductAmount productAmountInMarket : productAmountRepository.findAll()) {
            Product product = new Product(productAmountInMarket.getProduct().getName(), productAmountInMarket.getProduct().getCategory(), productAmountInMarket.getProduct().getPrice(), productAmountInMarket.getProduct().getDiscount(), productAmountInMarket.getProduct().getBarcode(), productAmountInMarket.getProduct().getDescription(), productAmountInMarket.getProduct().getImageURL());
            product.setId(productAmountInMarket.getProduct().getId());

            Market market = new Market(productAmountInMarket.getMarket().getName(), productAmountInMarket.getMarket().getCity(), productAmountInMarket.getMarket().getAddress(), productAmountInMarket.getMarket().getContact(), productAmountInMarket.getMarket().getEmail(), productAmountInMarket.getMarket().getImageURL());
            market.setId(productAmountInMarket.getMarket().getId());

            ProductAmount productAmount = new ProductAmount(productAmountInMarket.getAmount(), product, market);
            productAmount.setId(productAmountInMarket.getId());
            productAmounts.add(productAmount);
        }
        return productAmounts;
    }
}
