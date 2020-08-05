package com.onlineMarket.MarketInfoService.Controller;

import com.google.gson.Gson;
import com.onlineMarket.MarketInfoService.ActiveParameters;
import com.onlineMarket.MarketInfoService.Communication.MarketInfoServicePublisher;
import com.onlineMarket.MarketInfoService.Models.Product;
import com.onlineMarket.MarketInfoService.Models.ProductAmount;
import com.onlineMarket.MarketInfoService.Repositories.ProductAmountRepository;
import com.onlineMarket.MarketInfoService.Repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class ProductController {

    @Autowired
    ProductAmountRepository productAmountRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ActiveParameters activeParameters;

    @Autowired
    MarketInfoServicePublisher marketInfoServicePublisher;

    @RequestMapping(value = "productcatalog/product/all", method = RequestMethod.GET)
    public List<Product> getAllProductsOfSelectedMarket() {
        List<Product> products = new ArrayList<>();
        List<ProductAmount> productAmounts = productAmountRepository.findProductAmountsByMarket_Id(activeParameters.getActiveMarketID());

        for(ProductAmount productAmount: productAmounts) {
            products.add(productRepository.findById(productAmount.getProduct().getId()).get());
        }

        return products;
    }

    @RequestMapping(value = "productcatalog/market/name", method = RequestMethod.GET)
    public String getMarketNameOfSelectedMarket() {
        String marketName = new Gson().toJson(activeParameters.getActiveMarketName());
        return marketName;
    }

    @RequestMapping(value = "productcatalog/productamount/all", method = RequestMethod.GET)
    public List<ProductAmount> getAllProductAmountsOfSelectedMarket() {
        return activeParameters.getProductAmounts();
    }

    @RequestMapping(value = "productcatalog/product/add", method = RequestMethod.POST)
    public void addProductToShoppingCart(@RequestBody Map<String, String> data) throws Exception {
        Integer productAmount_id = Integer.parseInt(data.get("productAmount_id"));
        Integer amount = Integer.parseInt(data.get("amount"));
        activeParameters.reduceProductAmountByNumber(productAmount_id, amount);
        marketInfoServicePublisher.sendProductToShoppingCartMessage(data);
    }
}
