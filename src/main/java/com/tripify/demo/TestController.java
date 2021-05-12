package com.tripify.demo;

import com.tripify.demo.auth.payload.responses.SignUpResponse;
import com.tripify.demo.message_handler.ResponseBody;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("test/")
public class TestController {

    @GetMapping("hello")
    public ResponseBody<SignUpResponse> hello(){
        return new ResponseBody<>(new SignUpResponse("Hello"));
    }

}
