package com.mdem.komunalka.security;

import com.mdem.komunalka.exception.IncorrectPasswordException;
import com.mdem.komunalka.model.User;
import com.mdem.komunalka.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
public class LoginController {

    @Value("${security.headerName}")
    private String HEADER_NAME;

    @Autowired private UserService userService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    public void getAuthenticationToken(@RequestBody UserCredential credential, HttpServletResponse response) throws IncorrectPasswordException {

        User user = (User)userService.loadUserByUsername(credential.getUsername());
        String fullToken;

        if (user.getPassword().equals(credential.getPassword())) {
            fullToken = TokenAuthenticationService.createToken(user);
            response.addHeader(HEADER_NAME, fullToken);
        } else {
            throw new IncorrectPasswordException("Password is incorrect");
        }
    }
}