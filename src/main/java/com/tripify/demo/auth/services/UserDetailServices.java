package com.tripify.demo.auth.services;

import com.tripify.demo.auth.payload.UserPrincipal;
import com.tripify.demo.users.model.User;
import com.tripify.demo.users.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailServices implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String phone) throws UsernameNotFoundException {
        User user = userRepository.findByPhone(phone).orElseThrow(() ->
                new UsernameNotFoundException("User not found")
        );
        return UserPrincipal.create(user);
    }
}
