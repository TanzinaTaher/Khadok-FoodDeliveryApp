package com.example.khadokapp;

import com.google.firebase.database.Exclude;

public class AddToCartDB {
    String name,price;


    public String key;
    @Exclude
    public String getKey() {
        return key;
    }
    @Exclude
    public void setKey(String key) {
        this.key = key;
    }

    public AddToCartDB(String name, String price) {
        this.name = name;
        this.price = price;
    }

    public AddToCartDB()
    {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
