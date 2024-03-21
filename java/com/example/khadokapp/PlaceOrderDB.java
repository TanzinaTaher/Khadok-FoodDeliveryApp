package com.example.khadokapp;

public class PlaceOrderDB {

    String name,price,username;

    public PlaceOrderDB(String name, String price, String username) {
        this.name = name;
        this.price = price;
        this.username = username;
    }

    public PlaceOrderDB()
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
