package com.mdem.komunalka.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mdem.komunalka.exception.BadTokenException;
import com.mdem.komunalka.model.common.ErrorInfo;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@PropertySource("classpath:security.properties")
public class TokenAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    @Value("${security.headerName}")
    private String HEADER_NAME;

    private Logger logger;

    @Autowired
    public void setLogger(Logger logger) {
        this.logger = logger;
    }

    public TokenAuthenticationFilter(String url) {
        super(new AntPathRequestMatcher(url));
    }

    @Override
    @Autowired
    public void setAuthenticationManager(AuthenticationManager authenticationManager) {
        super.setAuthenticationManager(authenticationManager);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String token = request.getHeader(HEADER_NAME);

        if (token != null) {
            UserAuthentication userAuthentication = new UserAuthentication(token);
            try {
                return getAuthenticationManager().authenticate(userAuthentication);
            } catch (BadTokenException bte) {
                response.setContentType("application/json;charset=UTF-8");
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

                String errorURL = request.getRequestURL().toString();
                String errorMessage = bte.getMessage();
                ErrorInfo errorInfo = new ErrorInfo(HttpStatus.UNAUTHORIZED.value(), errorURL, errorMessage);

                PrintWriter out = response.getWriter();
                String jsonString = new ObjectMapper().writeValueAsString(errorInfo);

                out.print(jsonString);
                out.flush();

                logger.error(bte.getMessage(), bte);
            }

        } else {
            throw new BadCredentialsException("Token is not found");
        }
        return null;
    }

    @Override
    public void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication) throws IOException, ServletException {
        SecurityContextHolder.getContext().setAuthentication(authentication);
        response.setHeader(HEADER_NAME, TokenAuthenticationService.refreshToken(authentication));
        chain.doFilter(request, response);
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException {
        SecurityContextHolder.clearContext();

        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

        String errorURL = request.getRequestURL().toString();
        String errorMessage = exception.getMessage();
        ErrorInfo errorInfo = new ErrorInfo(HttpStatus.UNAUTHORIZED.value(), errorURL, errorMessage);

        PrintWriter out = response.getWriter();
        String jsonString = new ObjectMapper().writeValueAsString(errorInfo);

        out.print(jsonString);
        out.flush();

        logger.error(exception.getMessage(), exception);
    }
}