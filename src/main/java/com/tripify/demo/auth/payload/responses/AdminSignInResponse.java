package com.tripify.demo.auth.payload.responses;

public class AdminSignInResponse {

    public String token;
    
    public AdminSignInResponse(String token) {
        this.token = token;
    }
}
