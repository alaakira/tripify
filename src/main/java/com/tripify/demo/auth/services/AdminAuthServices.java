package com.tripify.demo.auth.services;

import com.tripify.demo.auth.jwt.JWTUtils;
import com.tripify.demo.auth.payload.UserPrincipal;
import com.tripify.demo.auth.payload.requests.AdminSignInRequest;
import com.tripify.demo.auth.payload.requests.AdminSignUpRequest;
import com.tripify.demo.auth.payload.responses.AdminSignInResponse;
import com.tripify.demo.auth.payload.responses.AdminSignUpResponse;
import com.tripify.demo.exceptions.IncorrectPasswordException;
import com.tripify.demo.exceptions.UserExistsException;
import com.tripify.demo.strings.Messages;
import com.tripify.demo.users.model.Admin;
import com.tripify.demo.users.repositories.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AdminAuthServices implements UserDetailsService {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private PasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private JWTUtils jwtUtils;

    public AdminSignUpResponse saveAdmin(AdminSignUpRequest request){
        Admin search = adminRepository.findByPhone(request.phone).orElse(null);
        if(search != null)
            throw new UserExistsException();
        Admin user = new Admin(request.phone, bCryptPasswordEncoder.encode(request.password));
        Admin result = adminRepository.save(user);
        UserPrincipal principal = UserPrincipal.create(result);
        String token = jwtUtils.generateToken(principal);
        return new AdminSignUpResponse(token);
    }

    public AdminSignInResponse signIn(AdminSignInRequest request){
        Admin admin = adminRepository.findByPhone(request.phone).orElseThrow(() -> new UsernameNotFoundException(Messages.UserNotFound));
        if(!bCryptPasswordEncoder.matches(request.password, admin.getPassword()))
            throw new IncorrectPasswordException();
        UserPrincipal principal = UserPrincipal.create(admin);
        String token = jwtUtils.generateToken(principal);
        return new AdminSignInResponse(token);
    }

    @Override
    public UserDetails loadUserByUsername(String phone) throws UsernameNotFoundException {
        Admin admin = adminRepository.findByPhone(phone).orElseThrow(() ->
                new UsernameNotFoundException("User not found")
        );
        return UserPrincipal.create(admin);
    }
}
