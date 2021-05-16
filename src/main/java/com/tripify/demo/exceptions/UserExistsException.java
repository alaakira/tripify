package com.tripify.demo.exceptions;

public class UserExistsException extends RuntimeExceptionTemplate{

    public static final String message = "";

    @Override
    public String getMessage() {
        return message;
    }
}
