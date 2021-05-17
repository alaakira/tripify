package com.tripify.demo.auth.payload;

import com.tripify.demo.users.model.Admin;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class UserPrincipal implements UserDetails {

    private Long id;
    private String email;
    private String password;
    private Collection<? extends GrantedAuthority> authorities;
    private Map<String, Object> attributes;
    private Admin admin;

    public UserPrincipal(Long id, String email, String password, Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.authorities = authorities;
    }

    public UserPrincipal(Long id, String email, String password, Collection<? extends GrantedAuthority> authorities, Admin admin) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.authorities = authorities;
        this.admin = admin;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }

    public void setAttributes(Map<String, Object> attributes) {
        this.attributes = attributes;
    }

    public static UserPrincipal create(Admin admin) {
        List<GrantedAuthority> authorities = new ArrayList<>();

        return new UserPrincipal(
                admin.getId(),
                admin.getPhone(),
                admin.getPassword(),
                authorities,
                admin
        );
    }

    public static UserPrincipal create(Admin admin, Map<String, Object> attributes) {
        UserPrincipal userPrincipal = UserPrincipal.create(admin);
        userPrincipal.setAttributes(attributes);
        return userPrincipal;
    }
}
