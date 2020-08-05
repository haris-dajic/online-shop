package com.onlineMarket.ShoppingCartService.Models;

import java.util.ArrayList;
import java.util.List;

public class ActiveParameters {

    int activeUserID;
    int activeMarketID;
    List<Product> activeProductList;
    //List<Integer> activeProductAmountList;
    String activeUserNameAndSurname;

    public ActiveParameters() {
        activeProductList = new ArrayList<>();
        //activeProductAmountList = new ArrayList<>();
    }

    public int getActiveUserID() {
        return activeUserID;
    }
    public void setActiveUserID(int activeUserID) {
        this.activeUserID = activeUserID;
    }

    public int getActiveMarketID() {
        return activeMarketID;
    }
    public void setActiveMarketID(int activeMarketID) {
        this.activeMarketID = activeMarketID;
    }

    public void addProduct(Product product) {
        activeProductList.add(product);
    }

    public List<Product> getActiveProductList() {
        return activeProductList;
    }
    public void setActiveProductList(List<Product> activeProductList) {
        this.activeProductList = activeProductList;
    }
    
    /*public List<Integer> getActiveProductAmountList() {
        return activeProductAmountList;
    }
    public void setActiveProductAmountList(List<Integer> activeProductAmountList) {
        this.activeProductAmountList = activeProductAmountList;
    }*/

    public String getActiveUserNameAndSurname() {
        return activeUserNameAndSurname;
    }

    public void setActiveUserNameAndSurname(String activeUserNameAndSurname) {
        this.activeUserNameAndSurname = activeUserNameAndSurname;
    }

    public void removeProductByID(Integer product_id) {
        for(Product product: activeProductList) {
            if(product.getId() == product_id)
                activeProductList.remove(product);
        }
    }

    public void reduceProductAmountByOne(Integer product_id) {
        for(Product product: activeProductList) {
            if(product.getId() == product_id)
                product.setAmount(product.getAmount() - 1);
        }
    }
}
