package com.mdem.komunalka.controller.common;

import com.mdem.komunalka.model.User;
import com.mdem.komunalka.service.impl.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@Api(tags = {"Registration"}, description="Operation for users registration")
public class RegistrationController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.CREATED)
    @ApiOperation(value = "Registration a new user")
    public void createUser(@RequestBody User user) {
        userService.create(user);
    }
}
