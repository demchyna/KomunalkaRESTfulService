package com.mdem.komunalka.security;

import com.mdem.komunalka.service.common.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CustomAuthenticationProcessingFilter extends AbstractAuthenticationProcessingFilter {

    @Autowired
    private TokenService tokenService;

    private String tokenHeaderName;

    public CustomAuthenticationProcessingFilter(String processingURL, String tokenHeaderName) {
        super(processingURL);
        this.tokenHeaderName = tokenHeaderName;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException, IOException, ServletException {
        String token = request.getHeader(tokenHeaderName);

        if (token == null) {
            throw new BadCredentialsException("Bad token!");
        }

        Authentication auth = new CustomAuthenticationToken(token);
        Authentication authentication = getAuthenticationManager().authenticate(auth);

        renewToken(response, authentication);

        return authentication;
    }

    private void renewToken(HttpServletResponse response, Authentication authentication) {
        if(authentication != null && authentication instanceof AuthenticatedUserProxy && authentication.isAuthenticated()) {
            CustomAuthenticationToken authenticationToken = ((AuthenticatedUserProxy) authentication).getAuthenticationToken();
            if(authenticationToken.isValid()) {
                response.setHeader(tokenHeaderName, tokenService.renewToken(authenticationToken));
            }
        }
    }
}