package com.example.restaurantapp.user;

public class LoginRequest {
    private String loginCode;

    private String password;

    public LoginRequest(String loginCode, String password) {
        this.loginCode = loginCode;
        this.password = password;
    }

    public String getLoginCode() {
        return loginCode;
    }

    public void setLoginCode(String loginCode) {
        this.loginCode = loginCode;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
