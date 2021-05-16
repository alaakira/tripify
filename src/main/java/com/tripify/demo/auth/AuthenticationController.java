package com.tripify.demo.auth;

import com.tripify.demo.auth.jwt.JWTUtils;
import com.tripify.demo.auth.payload.UserPrincipal;
import com.tripify.demo.auth.payload.requests.SignInRequest;
import com.tripify.demo.auth.payload.requests.SignUpRequest;
import com.tripify.demo.auth.payload.responses.SignInResponse;
import com.tripify.demo.auth.payload.responses.SignUpResponse;
import com.tripify.demo.auth.services.UserDetailServices;
import com.tripify.demo.consts.URLs;
import com.tripify.demo.exceptions.IncorrectPasswordException;
import com.tripify.demo.message_handler.ResponseBody;
import com.tripify.demo.strings.Messages;
import com.tripify.demo.users.model.User;
import com.tripify.demo.users.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(URLs.AUTH_CONTROLLER)
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserDetailServices userDetailServices;
    @Autowired
    private JWTUtils jwtUtils;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder bCryptPasswordEncoder;


    @PostMapping(URLs.SIGN_IN_URL)
    public ResponseBody<SignInResponse> signIn(@RequestBody SignInRequest signInRequest){
        User user = userRepository.findByPhone(signInRequest.phone).orElseThrow(() -> new UsernameNotFoundException(Messages.UserNotFound));
        if(!bCryptPasswordEncoder.matches(signInRequest.password,user.getPassword()))
            throw new IncorrectPasswordException();
        UserPrincipal principal = UserPrincipal.create(user);
        String token = jwtUtils.generateToken(principal);
        return new ResponseBody<>(new SignInResponse(token));
    }

    @PostMapping(URLs.SIGN_UP_URL)
    public ResponseBody<SignUpResponse> signUp(HttpServletRequest request, @RequestBody SignUpRequest signUpRequest){
        String token;
        User user = new User();
        user.setPassword(bCryptPasswordEncoder.encode(signUpRequest.password));
        user.setPhone(signUpRequest.phone);
        User temp = userRepository.findByPhone(signUpRequest.phone).orElse(null);
        if(temp != null)
            throw new UsernameNotFoundException(Messages.UserAlreadyExists);
        userRepository.save(user);
        UserPrincipal principal = UserPrincipal.create(user);
        token = jwtUtils.generateToken(principal);
        return new ResponseBody<>(new SignUpResponse(token));
    }

}
