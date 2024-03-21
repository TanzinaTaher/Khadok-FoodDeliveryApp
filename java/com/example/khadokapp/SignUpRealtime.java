package com.example.khadokapp;

public class SignUpRealtime {
    public SignUpRealtime(String fullname, String username, String email, String gender,String pass) {
        this.fullname = fullname;
        this.username = username;
        this.email = email;
        this.gender = gender;
        this.pass = pass;
    }
    public SignUpRealtime()
    {

    }
    public SignUpRealtime(String email,String pass)
    {
        this.email = email;
        this.pass = pass;
    }


    public String fullname,username,email,gender,pass;
}