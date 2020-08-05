package com.onlineMarket.ShoppingCartService.Models;

import com.onlineMarket.ShoppingCartService.CustomExceptionHandlers.*;

import javax.persistence.*;
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

    protected Market() {}

    public Market(Integer id, String name, String city, String address) {
        try {
            this.setId(id);
            this.setName(name);
            this.setCity(city);
            this.setAddress(address);
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
}
