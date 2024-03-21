package com.example.khadokapp;

public class Admin_CurrentOrder_Model {

    String TotalPrice,username,orders,currentTime,currentData,address,phonenumber;

    public Admin_CurrentOrder_Model(String totalPrice, String username, String orders, String currentTime, String currentData, String address, String phonenumber) {
        TotalPrice = totalPrice;
        this.username = username;
        this.orders = orders;
        this.currentTime = currentTime;
        this.currentData = currentData;
        this.address = address;
        this.phonenumber = phonenumber;
    }

    public Admin_CurrentOrder_Model(String totalPrice, String username, String orders, String currentTime, String currentData) {
        TotalPrice = totalPrice;
        this.username = username;
        this.orders = orders;
        this.currentTime = currentTime;
        this.currentData = currentData;
    }

    public Admin_CurrentOrder_Model()
    {

    }

    public String getTotalPrice() {
        return TotalPrice;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public void setTotalPrice(String totalPrice) {
        TotalPrice = totalPrice;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getOrders() {
        return orders;
    }

    public void setOrders(String orders) {
        this.orders = orders;
    }

    public String getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(String currentTime) {
        this.currentTime = currentTime;
    }

    public String getCurrentData() {
        return currentData;
    }

    public void setCurrentData(String currentData) {
        this.currentData = currentData;
    }
}
