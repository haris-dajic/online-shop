package com.OnlineMarket.AdminService.Controllers;

import com.OnlineMarket.AdminService.Communication.AdminServicePublisher;
import com.OnlineMarket.AdminService.Models.ProductAmount;
import com.OnlineMarket.AdminService.Repositories.ProductAmountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class ProductAmountController {

    @Autowired
    ProductAmountRepository productAmountRepository;

    @Autowired
    AdminServicePublisher adminServicePublisher;

    @RequestMapping(method = RequestMethod.PUT, value = "/productamount")
    public void updateProductAmount(Map<String, String> data) throws Exception {
        Integer product_id = Integer.parseInt(data.get("product_id"));
        Integer market_id = Integer.parseInt(data.get("market_id"));
        Integer amount = Integer.parseInt(data.get("amount"));

        ProductAmount updatedProductAmount = productAmountRepository.findProductAmountByMarket_IdAndProduct_Id(market_id, product_id);
        updatedProductAmount.setAmount(amount);

        data.put("product_amount_id", Integer.toString(productAmountRepository.save(updatedProductAmount).getId()));
        adminServicePublisher.sendProductAmountCreatedOrUpdatedMessage(data);
    }
}
