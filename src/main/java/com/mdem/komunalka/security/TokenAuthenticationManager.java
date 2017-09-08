package com.mdem.komunalka.security;

import com.mdem.komunalka.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
public class TokenAuthenticationManager implements AuthenticationManager {

    @Autowired private UserService userService;
    @Autowired private TokenAuthenticationService tokenAuthenticationService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        System.out.println("Hello from TokenAuthenticationManager!");



        return null;
    }
}
