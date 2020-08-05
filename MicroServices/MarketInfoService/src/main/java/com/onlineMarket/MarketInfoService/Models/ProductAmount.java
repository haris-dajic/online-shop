package com.onlineMarket.MarketInfoService.Models;

import com.onlineMarket.MarketInfoService.CustomExceptionHandlers.InvalidAmountException;

import javax.persistence.*;

@Entity
@Table(name = "PRODUCT_AMOUNT")
public class ProductAmount {

    @Id
    @Column(name = "PRODUCT_AMOUNT_ID")
    private int id;

    @Column(name = "AMOUNT")
    private int amount;

    @ManyToOne
    @JoinColumn(name = "PRODUCT_ID", nullable = false)
    private Product product;

    @ManyToOne
    @JoinColumn(name = "MARKET_ID", nullable = false)
    private Market market;

    public ProductAmount() {}

    public ProductAmount(Integer id, int amount, Product product, Market market) {
        try {
            this.setId(id);
            this.setAmount(amount);
            this.product = product;
            this.market = market;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public int getAmount() {
        return amount;
    }
    public void setAmount(int amount) throws InvalidAmountException {
        if(amount >= 0)
            this.amount = amount;
        else throw new InvalidAmountException("Invalid Amount!");
    }

    public Product getProduct() {
        return product;
    }
    public void setProduct(Product product) {
        this.product = product;
    }

    public Market getMarket() {
        return market;
    }
    public void setMarket(Market market) {
        this.market = market;
    }
    
}
