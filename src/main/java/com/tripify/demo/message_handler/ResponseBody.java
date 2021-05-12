package com.tripify.demo.message_handler;

import java.io.Serializable;

public class ResponseBody<T> {

    public int code = 200;

    public T data;

    public String message = "DEFAULT_MESSAGE";

    public ResponseBody(T data) {
        this.data = data;
    }

    public ResponseBody(T data, String message) {
        this.data = data;
        this.message = message;
    }
}
