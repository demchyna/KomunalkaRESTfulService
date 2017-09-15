package com.mdem.komunalka.security;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mdem.komunalka.model.Role;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TokenAuthenticationManager implements AuthenticationManager {

    ObjectMapper mapper = new ObjectMapper();

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        UserAuthentication userAuthentication = (UserAuthentication) authentication;

        try {
            long userId = TokenAuthenticationService.getUserIdFromToken(userAuthentication.getToken());
            String username = TokenAuthenticationService.getUsernameFromToken(userAuthentication.getToken());
            List<Role> authorities = mapper.convertValue(
                    TokenAuthenticationService.getRolesFromToken(userAuthentication.getToken()),
                    new TypeReference<List<Role>>() { }
            );


            Principal principal = new Principal(userId, username, authorities);
            userAuthentication.setPrincipal(principal);
            userAuthentication.setAuthenticated(true);

        } catch (SignatureException se) {
            System.out.println(se.getMessage());
            throw new BadCredentialsException("Token is not valid");
        } catch (ExpiredJwtException ee) {
            System.out.println(ee.getMessage());
            throw new BadCredentialsException("Token must be refreshed");
        }

        return userAuthentication;
    }
}