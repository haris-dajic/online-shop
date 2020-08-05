package com.OnlineMarket.AdminService.Controllers;

import com.OnlineMarket.AdminService.Communication.AdminServicePublisher;
import com.OnlineMarket.AdminService.Models.Market;
import com.OnlineMarket.AdminService.Repositories.MarketRepository;
import com.OnlineMarket.AdminService.Repositories.ProductAmountRepository;
import com.OnlineMarket.AdminService.Repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Map;

@RestController
public class MarketController {

    @Autowired
    ProductRepository productRepository;
    @Autowired
    MarketRepository marketRepository;
    @Autowired
    ProductAmountRepository productAmountRepository;
    @Autowired
    AdminServicePublisher adminServicePublisher;

    @RequestMapping(method = RequestMethod.POST, value = "/market")
    public void addMarket(@RequestBody Map<String, String> data) {
        Market newMarket = getMarketData(data);
        marketRepository.save(newMarket);
        adminServicePublisher.sendMarketCreatedOrUpdatedMessage(newMarket);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/market")
    public void updateMarket(@RequestBody Map<String, String> data) throws Exception {
        Market updatedMarket = getMarketData(data);

        int market_id = Integer.parseInt(data.get("market_id"));
        updatedMarket.setId(market_id);

        marketRepository.save(updatedMarket);
        adminServicePublisher.sendMarketCreatedOrUpdatedMessage(updatedMarket);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/market/{market_id}")
    public void deleteMarket(@PathVariable("market_id") int market_id) {
        marketRepository.deleteById(market_id);
        productAmountRepository.deleteAllByMarket_Id(market_id);

        adminServicePublisher.sendMarketDeletedMessage(market_id);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/market/all")
    public Iterable<Market> getAllMarkets() throws Exception{
        Iterable<Market> markets = new ArrayList<>();
        markets = marketRepository.findAll();
        return markets;
    }

    public Market getMarketData(Map<String, String> data) {
        String name = data.get("name");
        String contact = data.get("contact");
        String email = data.get("email");
        String city = data.get("city");
        String address = data.get("address");
        String imageURL = data.get("imageURL");

        return new Market(name, city, address, contact, email, imageURL);
    }
}
