package com.mdem.komunalka.security;

import com.mdem.komunalka.model.User;
import com.mdem.komunalka.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

@Service
public class TokenAuthenticationManager implements AuthenticationManager {

    @Autowired private UserService userService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        UserAuthentication userAuthentication = (UserAuthentication) authentication;

        String username = TokenAuthenticationService.getUsernameFromToken(userAuthentication.getToken());

        User user = userService.getUserByLogin(username);


        userAuthentication.setPrincipal(user);
        userAuthentication.setAuthenticated(true);

        System.out.println(userAuthentication.getToken());
        for (GrantedAuthority role : userAuthentication.getAuthorities()) {
            System.out.println(role.getAuthority());
        }


        return userAuthentication;
    }
}