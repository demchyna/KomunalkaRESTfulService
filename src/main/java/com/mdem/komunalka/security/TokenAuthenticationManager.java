package com.mdem.komunalka.security;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mdem.komunalka.model.Role;
import com.mdem.komunalka.model.User;
import com.mdem.komunalka.service.impl.UserService;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TokenAuthenticationManager implements AuthenticationManager {

    ObjectMapper mapper = new ObjectMapper();

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        UserAuthentication userAuthentication = (UserAuthentication) authentication;

        try {
            String username = TokenAuthenticationService.getUsernameFromToken(userAuthentication.getToken());
            List<Role> authorities = mapper.convertValue(
                    TokenAuthenticationService.getRolesFromToken(userAuthentication.getToken()),
                    new TypeReference<List<Role>>() { }
            );

            Principal principal = new Principal(username, authorities);
            userAuthentication.setPrincipal(principal);
            userAuthentication.setAuthenticated(true);

        } catch (SignatureException se) {
            System.out.println(se.getMessage());
            throw new AuthenticationCredentialsNotFoundException("Token is not valid");
        } catch (ExpiredJwtException ee) {
            System.out.println(ee.getMessage());
            throw new AuthenticationCredentialsNotFoundException("Token must be rejected");
        }

        return userAuthentication;
    }
}