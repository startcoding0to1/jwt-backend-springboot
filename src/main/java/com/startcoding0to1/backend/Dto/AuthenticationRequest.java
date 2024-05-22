package com.startcoding0to1.backend.Dto;

public class AuthenticationRequest {
    private String userName;
    private String userPassword;

    public AuthenticationRequest(String userName, String password) {
        this.userName = userName;
        this.userPassword = password;
    }

    public AuthenticationRequest() {
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }
}
