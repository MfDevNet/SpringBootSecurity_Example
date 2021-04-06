package com.example.demo.jwt;

public class UsernameAndPasswordAuthenticationRequest {
     private String userName;
     private String Password;

    public UsernameAndPasswordAuthenticationRequest() {
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }
}
