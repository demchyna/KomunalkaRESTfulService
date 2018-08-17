package com.mdem.komunalka.controller.common;

import com.mdem.komunalka.model.Role;
import com.mdem.komunalka.model.User;
import com.mdem.komunalka.service.impl.RoleService;
import com.mdem.komunalka.service.impl.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@RestController
@Api(tags = {"Registration"}, description="Operation for users registration")
public class RegistrationController {

    private UserService userService;
    private RoleService roleService;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public RegistrationController(UserService userService, RoleService roleService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userService = userService;
        this.roleService = roleService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.CREATED)
    @ApiOperation(value = "Registration a new user")
    public void createUser(@Validated @RequestBody User user) {
        user.setAuthorities(Collections.singletonList(roleService.getById(2L)));
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userService.create(user);
    }
}
