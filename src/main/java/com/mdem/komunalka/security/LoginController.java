package com.mdem.komunalka.security;

import com.mdem.komunalka.exception.IncorrectPasswordException;
import com.mdem.komunalka.model.User;
import com.mdem.komunalka.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
public class LoginController {

    private static final String HEADER_STRING = "Authentication";
    private static final String TOKEN_PREFIX = "Bearer";

    @Autowired private UserService userService;
    @Autowired private TokenAuthenticationService tokenAuthenticationService;


    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    public void getAuthenticationToken(@RequestBody UserCredential credential, HttpServletResponse response) throws IncorrectPasswordException {

        User user = userService.getUserByLogin(credential.getLogin());
        String token;

        if (user.getPassword().equals(credential.getPassword())) {
            token = tokenAuthenticationService.createTokenAuthentication(credential.getLogin());
            response.addHeader(HEADER_STRING, TOKEN_PREFIX + " " + token);
        } else {
            throw new IncorrectPasswordException("Password is incorrect");
        }
    }
}