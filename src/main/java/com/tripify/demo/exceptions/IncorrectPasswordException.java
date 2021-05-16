package com.tripify.demo.exceptions;

public class IncorrectPasswordException extends RuntimeExceptionTemplate{

    public static final String message = "Incorrect Password";

    @Override
    public String getMessage() {
        return message;
    }
}
