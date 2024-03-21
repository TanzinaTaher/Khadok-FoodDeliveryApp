package com.example.khadokapp;

import com.google.firebase.database.Exclude;

public class PlaceOrder_Model {

    String price,username,name;

    String key;
    @Exclude
    public String getKey() {
        return key;
    }
    @Exclude
    public void setKey(String key) {
        this.key = key;
    }

    public PlaceOrder_Model(String price, String username, String name) {
        this.price = price;
        this.username = username;
        this.name = name;
    }
    public PlaceOrder_Model()
    {

    }
    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
