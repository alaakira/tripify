package com.tripify.demo.auth;

import com.tripify.demo.auth.jwt.JWTUtils;
import com.tripify.demo.auth.payload.UserPrincipal;
import com.tripify.demo.auth.payload.requests.SignInRequest;
import com.tripify.demo.auth.payload.requests.SignUpRequest;
import com.tripify.demo.auth.payload.responses.SignInResponse;
import com.tripify.demo.auth.payload.responses.SignUpResponse;
import com.tripify.demo.auth.services.UserDetailServices;
import com.tripify.demo.consts.URLs;
import com.tripify.demo.message_handler.ResponseBody;
import com.tripify.demo.users.model.User;
import com.tripify.demo.users.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/auth")
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
    public ResponseBody signIn(@RequestBody SignInRequest signInRequest){
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signInRequest.phone, signInRequest.password));
        UserDetails principal = userDetailServices.loadUserByUsername(signInRequest.phone);
        String token = jwtUtils.generateToken(principal);
        return new ResponseBody(new SignInResponse(token));
    }

    @PostMapping(URLs.SIGN_UP_URL)
    public ResponseBody signUp(HttpServletRequest request, @RequestBody SignUpRequest signUpRequest){
//        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signInRequest.phone, signInRequest.password));
//        UserDetails principal = userDetailServices.loadUserByUsername(signInRequest.phone);
//        String token = jwtUtils.generateToken(principal);
//        return new ResponseBody(new SignInResponse(token));
        UserDetails details = null;//userDetailServices.loadUserByUsername(signUpRequest.phone);
        String token;
        if(details == null){
            User user = new User();
            user.setPassword(bCryptPasswordEncoder.encode(signUpRequest.password));
            user.setPhone(signUpRequest.phone);
//            userRepository.save(user);
            UserPrincipal principal = UserPrincipal.create(user);
            System.out.println(getClass().getSimpleName()+" : "+principal.getEmail());
            System.out.println(getClass().getSimpleName()+" : "+user.getPassword());
            System.out.println(getClass().getSimpleName()+" : "+user.getId());
            token = jwtUtils.generateToken(principal);
            return new ResponseBody(token);
        }else
            throw new UsernameNotFoundException("User already exists");
    }

}
