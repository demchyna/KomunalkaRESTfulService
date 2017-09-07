package com.mdem.komunalka.security;

import com.mdem.komunalka.model.Role;
import com.mdem.komunalka.model.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.List;

public class UserAuthentication implements Authentication {

    private String token;
    private User user;
    private boolean authenticated;

    public UserAuthentication(String token, User user) {
        this.token = token;
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return user.getRoles();
    }

    @Override
    public Object getCredentials() {
        return user.getPassword();
    }

    @Override
    public Object getDetails() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return user;
    }

    @Override
    public boolean isAuthenticated() {
        return this.authenticated;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        this.authenticated = isAuthenticated;
    }


    @Override
    public String getName() {
        return user.getLogin();
    }


}
