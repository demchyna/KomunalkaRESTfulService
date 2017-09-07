package com.mdem.komunalka.controller.common;

import com.mdem.komunalka.security.TokenAuthenticationService;
import com.mdem.komunalka.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
public class TokenAuthenticationController {

    @Autowired private TokenAuthenticationService tokenAuthenticationService;
    @Autowired private UserService userService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public void login(@RequestParam String login, @RequestParam String password) {
        System.out.println("Hello TokenAuthenticationController!");
        System.out.println(login + ", " + password);
    }
}
