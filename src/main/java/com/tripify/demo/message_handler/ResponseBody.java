package com.tripify.demo.message_handler;

@org.springframework.web.bind.annotation.ResponseBody
public class ResponseBody {

    int code = 200;

    Object data;

    String message = "DEFAULT_MESSAGE";

    public ResponseBody(Object data) {
        this.data = data;
    }

    public ResponseBody(Object data, String message) {
        this.data = data;
        this.message = message;
    }
}
