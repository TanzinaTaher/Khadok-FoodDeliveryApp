package com.example.khadokapp;

public class Admin_PostUpload {

    String title,image,description;

    public Admin_PostUpload(String title, String image, String description) {
        this.title = title;
        this.image = image;
        this.description = description;
    }

    public Admin_PostUpload() {
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
