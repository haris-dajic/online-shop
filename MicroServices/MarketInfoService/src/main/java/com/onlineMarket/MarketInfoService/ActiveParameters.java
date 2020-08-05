package com.onlineMarket.MarketInfoService;

import com.onlineMarket.MarketInfoService.Models.ProductAmount;

import java.util.ArrayList;
import java.util.List;

public class ActiveParameters {

    Integer activeUserID = -1;
    Integer activeMarketID = -1;
    String activeNameAndSurname;
    String activeRole;
    String activeMarketName = "";
    List<ProductAmount> productAmounts = new ArrayList<>();

    public ActiveParameters() {
    }

    public Integer getActiveUserID() {
        return activeUserID;
    }
    public void setActiveUserID(Integer activeUserID) {
        this.activeUserID = activeUserID;
    }

    public Integer getActiveMarketID() {
        return activeMarketID;
    }
    public void setActiveMarketID(Integer activeMarketID) {
        this.activeMarketID = activeMarketID;
    }

    public String getActiveMarketName() {
        return activeMarketName;
    }
    public void setActiveMarketName(String activeMarketName) {
        this.activeMarketName = activeMarketName;
    }

    public String getActiveNameAndSurname() {
        return activeNameAndSurname;
    }
    public void setActiveNameAndSurname(String activeNameAndSurname) {
        this.activeNameAndSurname = activeNameAndSurname;
    }

    public String getActiveRole() {
        return activeRole;
    }
    public void setActiveRole(String activeRole) {
        this.activeRole = activeRole;
    }

    public List<ProductAmount> getProductAmounts() {
        return productAmounts;
    }
    public void setProductAmounts(List<ProductAmount> productAmounts) {
        this.productAmounts = productAmounts;
    }

    public void reduceProductAmountByNumber(Integer productAmount_id, Integer number) throws Exception {
        for(ProductAmount productAmount: productAmounts) {
            if(productAmount.getId() == productAmount_id)
                productAmount.setAmount(productAmount.getAmount() - number);
        }
    }

    public void increaseProductAmountByNumber(Integer product_id, Integer number) throws Exception {
        for(ProductAmount productAmount: productAmounts) {
            if(productAmount.getProduct().getId() == product_id)
                productAmount.setAmount(productAmount.getAmount() + number);
        }
    }

    public void increaseProductAmountByOne(Integer product_id) throws Exception {
        for(ProductAmount productAmount: productAmounts) {
            if(productAmount.getProduct().getId() == product_id)
                productAmount.setAmount(productAmount.getAmount() + 1);
        }
    }

}
