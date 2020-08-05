package com.onlineMarket.ShoppingCartService.Models;

import com.onlineMarket.ShoppingCartService.CustomExceptionHandlers.InvalidTotalException;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Entity
@Table(name = "ORDER")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ORDER_ID")
    private int id;

    @Column(name = "TOTAL")
    private double total;

    @Column(name = "DATE_TIME")
    private String dateTime;

    @Column(name = "USER_ID")
    private int user_id;

    @Column(name = "MARKET_ID")
    private int market_id;

    @ManyToMany
    @JoinTable(name = "ORDER_PRODUCT", joinColumns = {@JoinColumn(name = "ORDER_ID")},
            inverseJoinColumns = {@JoinColumn(name = "PRODUCT_ID")})
    private List<Product> products = new ArrayList<>();

    //List<String> productQuantities = new ArrayList<>();

    public Order() {}

    public Order(List<Product> products, double total, int user_id, int market_id) {
        try {
            this.setProducts(products);
            this.setTotal(total);
            this.setUser_id(user_id);
            this.setMarket_id(market_id);
            setDateTime(new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime()));
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int getId() {
        return id;
    }

    public List<Product> getProducts() {
        return products;
    }
    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public double getTotal() {
        return total;
    }
    public void setTotal(double total) throws InvalidTotalException {
        if(total < 0)
            throw new InvalidTotalException("Invalid total. Order total must be positive!");
        else
            this.total = total;
    }

    public String getDateTime() {
        return dateTime;
    }
    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    /*public List<String> getProductAmounts() {
        return productQuantities;
    }
    public void setProductAmounts(List<String> productQuantities) {
        this.productQuantities = productQuantities;
    }
*/
    public int getUser_id() {
        return user_id;
    }
    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getMarket_id() {
        return market_id;
    }
    public void setMarket_id(int market_id) {
        this.market_id = market_id;
    }
}
