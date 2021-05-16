package com.tripify.demo.exceptions;

public class ExpiredJwtToken extends Exception{

    private static final String message = "Token has been expired";

    public ExpiredJwtToken() {
        super(message);
    }
}
