package com.mdem.komunalka.security;

import com.mdem.komunalka.model.User;
import com.mdem.komunalka.service.common.TokenService;
import com.mdem.komunalka.service.impl.UserService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CustomAuthenticationManager implements AuthenticationManager {

    private final TokenService tokenService;
    private final UserService userService;

    public CustomAuthenticationManager(TokenService tokenService, UserService userService) {
        this.tokenService = tokenService;
        this.userService = userService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        CustomAuthenticationToken authenticationToken = (CustomAuthenticationToken) authentication;

        if (authenticationToken == null) {
            throw new BadCredentialsException("Token required to perform authentication");
        }

        tokenService.validate(authenticationToken);
        if(!authenticationToken.isValid()){
            throw new BadCredentialsException("Token is not valid");
        }

        User user = tokenService.getUserByToken(authenticationToken);
        if (user == null) {
            throw new UsernameNotFoundException("Username not found");
        }

        userService.verifyPremiumAccountValidity(user);
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(user.getRole());

        Authentication authenticatedUser = new AuthenticatedUserProxy(user, authenticationToken, true, authorities);
        return authenticatedUser;
    }
}