package com.example.khadokapp;

public class Admin_Upload {

    String name,image,price,id;

    public Admin_Upload(String name, String image, String price, String id) {
        this.name = name;
        this.image = image;
        this.price = price;
        this.id = id;
    }
    public Admin_Upload()
    {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
