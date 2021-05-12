package com.tripify.demo.auth.exceptions;

public class ExpiredJwtToken extends Exception{

    private static String message = "Token has been expired";

    public ExpiredJwtToken() {
        super(message);
    }
}
