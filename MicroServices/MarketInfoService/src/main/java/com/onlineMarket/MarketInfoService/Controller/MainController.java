package com.onlineMarket.MarketInfoService.Controller;


import com.google.gson.Gson;
import com.onlineMarket.MarketInfoService.ActiveParameters;
import com.onlineMarket.MarketInfoService.Communication.MarketInfoServicePublisher;
import com.onlineMarket.MarketInfoService.Models.Market;
import com.onlineMarket.MarketInfoService.Repositories.MarketRepository;
import com.onlineMarket.MarketInfoService.Repositories.ProductAmountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class MainController {

    @Autowired
    MarketRepository marketRepository;

    @Autowired
    ProductAmountRepository productAmountRepository;

    @Autowired
    MarketInfoServicePublisher marketInfoServicePublisher;

    @Autowired
    ActiveParameters activeParameters;

    @RequestMapping(value = "/market/all", method = RequestMethod.GET)
    public Iterable<Market> getAllMarkets() {
        return marketRepository.findAll();
    }

    @RequestMapping(value = "/market", method = RequestMethod.POST)
    public void sendMarketId(@RequestBody Map<String, String> data) {
        int market_id = Integer.parseInt(data.get("market_id"));
        Market selectedMarket = marketRepository.findById(market_id).get();
        marketInfoServicePublisher.sendMarketIDMessage(market_id);
        activeParameters.setActiveMarketID(market_id);
        activeParameters.setActiveMarketName(selectedMarket.getName());
        activeParameters.setProductAmounts(productAmountRepository.findProductAmountsByMarket_Id(market_id));
    }

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public Map<String, String> getUserInfo() {
        Map<String, String> data = new HashMap<>();
        data.put("username", activeParameters.getActiveNameAndSurname());
        data.put("role", activeParameters.getActiveRole());
        return data;
    }

}
