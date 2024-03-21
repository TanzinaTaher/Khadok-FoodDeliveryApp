package com.example.khadokapp;

import com.google.firebase.database.Exclude;

import java.io.Serializable;

public class Model2 implements Serializable {

    String image,name,price;

    private String key;

    @Exclude
    public String getKey() {
        return key;
    }
    @Exclude
    public void setKey(String key) {
        this.key = key;
    }

    public Model2(String image, String name, String price) {
        this.image = image;
        this.name = name;
        this.price = price;
    }

    public Model2()
    {

    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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
