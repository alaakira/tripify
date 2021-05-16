package com.tripify.demo.auth.payload.responses;

public class SignInResponse {

    public String token;
    
    public SignInResponse(String token) {
        this.token = token;
    }
}
