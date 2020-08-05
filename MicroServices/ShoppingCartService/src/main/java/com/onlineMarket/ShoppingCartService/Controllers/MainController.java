package com.onlineMarket.ShoppingCartService.Controllers;

import com.onlineMarket.ShoppingCartService.Models.ActiveParameters;
import com.onlineMarket.ShoppingCartService.Communication.ShoppingCartServicePublisher;
import com.onlineMarket.ShoppingCartService.Models.Product;
import com.onlineMarket.ShoppingCartService.Models.User;
import com.onlineMarket.ShoppingCartService.Repositories.UserRepository;
import com.onlineMarket.ShoppingCartService.Repositories.OrderRepository;
import com.onlineMarket.ShoppingCartService.Repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class MainController {
    @Autowired
    ProductRepository productRepository;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    ShoppingCartServicePublisher shoppingCartServicePublisher;

    @Autowired
    ActiveParameters activeParameters;

    @Autowired
    UserRepository userRepository;

    @RequestMapping(value = "/product/all", method = RequestMethod.GET)
    public List<Product> getProducts() {
        return activeParameters.getActiveProductList();
    }

    @RequestMapping(value = "/product", method = RequestMethod.PUT)
    public void removeProduct(@RequestBody Map<String, String> data) {
        Integer product_id = Integer.parseInt(data.get("id"));
        shoppingCartServicePublisher.sendProductRemovedMessage(data);
        activeParameters.removeProductByID(product_id);
    }

    @RequestMapping(value = "/product/lower", method = RequestMethod.PUT)
    public void lowerProductAmountByOne(@RequestBody Map<String, String> data) {
        Integer product_id = Integer.parseInt(data.get("id"));
        activeParameters.reduceProductAmountByOne(product_id);
        shoppingCartServicePublisher.sendUpdatedProductAmountMessage(data);
    }

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public User getActiveUser() {
        return userRepository.findById(activeParameters.getActiveUserID()).get();
    }
/*
    @RequestMapping(value = "/quantity/all", method = RequestMethod.GET)
    public List<String> getQuantitties() {
        return activeParameters.getActiveProductQuantitiesList();
    }

    @RequestMapping(value="/addproduct", method=RequestMethod.POST)
    public List<Product> addProduct(@RequestBody Product product){
        activeParameters.getActiveOrderList().add(product);

        return activeParameters.getActiveOrderList();
    }

    @RequestMapping(value= "/removeorder", method = RequestMethod.DELETE)
    public void removeOrder() throws InvalidTotalException {

        activeParameters.getActiveOrderList().clear();
        activeParameters.getActiveProductQuantitiesList().clear();
    }



    @RequestMapping(value= "/removeitem", method = RequestMethod.PUT)
    public void removeItem(@RequestBody Product product) throws InvalidTotalException {

        for(Product p : activeParameters.getActiveOrderList()){
            if(p.getId() == product.getId()) {
                activeParameters.getActiveProductQuantitiesList().remove(activeParameters.getActiveOrderList().indexOf(p));
                activeParameters.getActiveOrderList().remove(activeParameters.getActiveOrderList().indexOf(p));
            }
        }
    }*/
}
