package com.example.khadokapp;

import com.google.firebase.database.Exclude;

public class Admin_Model_Food {

    String name,price,image;


    private String key;

        public Admin_Model_Food(String name, String price, String image) {
            this.name = name;
            this.price = price;
            this.image = image;
        }
    public Admin_Model_Food()
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

    @Exclude
    public String getKey() {
        return key;
    }
    @Exclude
    public void setKey(String key) {
        this.key = key;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
