package com.example.khadokapp;

public class AddToCartDB1 {


    String name,price,username;

    public AddToCartDB1(String name, String price) {
        this.name = name;
        this.price = price;
    }

    public AddToCartDB1(String name, String price, String username) {
        this.name = name;
        this.price = price;
        this.username = username;
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

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
