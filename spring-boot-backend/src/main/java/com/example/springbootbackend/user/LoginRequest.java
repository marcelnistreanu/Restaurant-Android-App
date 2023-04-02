package com.example.springbootbackend.user;

public class LoginRequest {

    String loginCode;

    String password;

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

    @Override
    public String toString() {
        return "LoginRequest{" +
                "loginCode='" + loginCode + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
