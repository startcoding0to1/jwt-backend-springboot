package com.startcoding0to1.backend.Dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.startcoding0to1.backend.entity.User;

public class AuthenticationResponse {
    private User user;
    @JsonProperty("access_token")
    private String jwtToken;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getJwtToken() {
        return jwtToken;
    }

    public void setJwtToken(String jwtToken) {
        this.jwtToken = jwtToken;
    }
}
