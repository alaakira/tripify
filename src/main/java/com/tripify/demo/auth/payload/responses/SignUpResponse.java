package com.tripify.demo.auth.payload.responses;

import java.io.Serializable;

public class SignUpResponse implements Serializable {
    String token;

    public SignUpResponse() {
    }

    public SignUpResponse(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
