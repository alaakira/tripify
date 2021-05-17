package com.tripify.demo.auth.controllers;

import com.tripify.demo.auth.payload.requests.AdminSignInRequest;
import com.tripify.demo.auth.payload.requests.AdminSignUpRequest;
import com.tripify.demo.auth.payload.responses.AdminSignInResponse;
import com.tripify.demo.auth.payload.responses.AdminSignUpResponse;
import com.tripify.demo.auth.services.AdminAuthServices;
import com.tripify.demo.consts.URLs;
import com.tripify.demo.message_handler.ResponseBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(URLs.AUTH_CONTROLLER+URLs.ADMIN_PATH)
public class AdminAuthenticationController {

    private final AdminAuthServices adminAuthServices;

    public AdminAuthenticationController(AdminAuthServices adminAuthServices) {
        this.adminAuthServices = adminAuthServices;
    }

    @PostMapping(URLs.ADMIN_SIGN_IN_URL)
    public ResponseBody<AdminSignInResponse> signInAdmin(@RequestBody AdminSignInRequest adminSignInRequest){
        AdminSignInResponse response = adminAuthServices.signIn(adminSignInRequest);
        return new ResponseBody<>(response);
    }

//    @PostMapping(URLs.ADMIN_SIGN_UP_URL)
    public ResponseBody<AdminSignUpResponse> signUpAdmin(HttpServletRequest request, @RequestBody AdminSignUpRequest adminSignUpRequest){
        AdminSignUpResponse response = adminAuthServices.saveAdmin(adminSignUpRequest);
        return new ResponseBody<>(response);
    }

}
