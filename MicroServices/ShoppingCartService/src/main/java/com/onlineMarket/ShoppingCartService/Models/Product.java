package com.onlineMarket.ShoppingCartService.Models;

import com.onlineMarket.ShoppingCartService.CustomExceptionHandlers.*;

import javax.persistence.*;
import java.util.regex.Pattern;

@Entity
@Table(name = "PRODUCT")
public class Product {

    @Id
    @Column(name = "PRODUCT_ID")
    private int id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "CATEGORY")
    private String category;

    @Column(name = "PRICE")
    private double price;

    @Column(name = "DISCOUNT")
    private double discount;

    @Column(name = "BARCODE")
    private String barcode;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "IMAGE_URL")
    private String imageURL;

    private int amount = 0;

    public Product() {}

    public Product(Integer id, String name, String category, double price, double discount, String barcode, String description, String imageURL) {
        try {
            this.setId(id);
            this.setName(name);
            this.setCategory(category);
            this.setPrice(price);
            this.setDiscount(discount);
            this.setBarcode(barcode);
            this.setDescription(description);
            this.setImageURL(imageURL);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) throws InvalidNameException {
        String regex = "^[A-ZČĆŽŠĐ][A-Za-z0-9\\s-čČćĆžŽšŠđĐ]*";
        if(Pattern.matches(regex, name))
            this.name = name;
        else throw new InvalidNameException("Invalid Product Name");
    }

    public String getCategory() {
        return category;
    }
    public void setCategory(String category) throws InvalidCategoryException {
        String regex = "^[A-Z][A-Za-z0-9\\s-čČćĆžŽšŠđĐ]*";
        if(Pattern.matches(regex, category))
            this.category = category;
        else throw new InvalidCategoryException("Invalid Category!");
    }

    public double getPrice() {
        return price;
    }
    public void setPrice(double price) throws InvalidPriceException {
        if(price > 0)
            this.price = price;
        else throw new InvalidPriceException("Invalid Price!");
    }

    public double getDiscount() {
        return discount;
    }
    public void setDiscount(double discount) throws InvalidDiscountException {
        if(discount < 0 || discount > 100)
            throw new InvalidDiscountException("Invalid Sale Percentage!");
        else
            this.discount = discount;
    }

    public String getBarcode() {
        return barcode;
    }
    public void setBarcode(String barcode) throws InvalidBarcodeException {
        String regex = "[0-9]{8,}";
        if(Pattern.matches(regex, barcode))
            this.barcode = barcode;
        else throw new InvalidBarcodeException("Invalid Barcode!");
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageURL() {
        return imageURL;
    }
    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public int getAmount() {
        return amount;
    }
    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void addAmount(int amount) {
        this.amount += amount;
    }
}
