package com.tripify.demo;

import com.tripify.demo.auth.payload.responses.AdminSignUpResponse;
import com.tripify.demo.message_handler.ResponseBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("test/")
public class TestController {

    @GetMapping("hello")
    public ResponseBody<AdminSignUpResponse> hello(){
        return new ResponseBody<>(new AdminSignUpResponse("Hello"));
    }

}
