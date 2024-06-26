package com.example.khadokapp;

import com.google.firebase.database.Exclude;

public class Admin_Model_Post {


    String title,image,description;

    private String key;


    @Exclude
    public String getKey() {
        return key;
    }
    @Exclude
    public void setKey(String key) {
        this.key = key;
    }

    public Admin_Model_Post(String title, String image, String description) {
        this.title = title;
        this.image = image;
        this.description = description;
    }

    public Admin_Model_Post() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
