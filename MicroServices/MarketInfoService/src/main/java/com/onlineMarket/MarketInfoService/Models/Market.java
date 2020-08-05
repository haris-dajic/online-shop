package com.onlineMarket.MarketInfoService.Models;

import com.onlineMarket.MarketInfoService.CustomExceptionHandlers.*;

import javax.persistence.*;
import java.util.List;
import java.util.regex.Pattern;

@Entity
@Table(name = "MARKET")
public class Market {

    @Id
    @Column(name = "MARKET_ID")
    private int id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "CITY")
    private String city;

    @Column(name = "ADDRESS")
    private String address;

    @Column(name = "CONTACT")
    private String contact;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "IMAGE_URL")
    private String imageURL;

    @OneToMany
    private List<ProductAmount> markets;

    protected Market() {}

    public Market(Integer id, String name, String city, String address, String contact, String email, String imageURL) {
        try {
            this.setId(id);
            this.setName(name);
            this.setCity(city);
            this.setAddress(address);
            this.setContact(contact);
            this.setEmail(email);
            this.setImageURL(imageURL);
        } catch (Exception e) {
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
        else throw new InvalidNameException("Invalid Market Name!");
    }

    public String getCity() {
        return city;
    }
    public void setCity(String city) throws InvalidCityException {
        if(!city.matches("^[A-ZČŽŠĆĐ][a-zčžšćđ]+(?:[\\s-][A-ZČŽŠĆĐ][a-zčžšćđ]+)*$"))
            throw new InvalidCityException("Invalid City Name!");
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) throws InvalidAddressException {
        if(!address.matches("^[A-ZČŽŠĆĐ][a-zčžšćđ]+(\\s?[A-ZČŽŠĆĐ][a-zčžšćđ]*)*\\s?\\d{1,3}"))
            throw new InvalidAddressException("Invalid Address! (primjer: Bulevar Meše Selimovića 2 ili Drinska 324)");
        this.address = address;
    }

    public String getContact() {
        return contact;
    }
    public void setContact(String contact) throws InvalidContactException {
        if(!contact.matches("^[+][0-9]{10,17}$"))
            throw new InvalidContactException("Invalid Contact Number!");
        this.contact = contact;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) throws InvalidEmailException {
        if(!email.matches("^[A-Za-z0-9-+_.]+@[a-zA-Z0-9-.]+\\.(com|ba)$"))
            throw new InvalidEmailException("Invalid Email!");
        this.email = email;
    }

    public String getImageURL() {
        return imageURL;
    }
    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }
}
