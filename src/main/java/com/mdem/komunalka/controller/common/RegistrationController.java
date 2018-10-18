package com.mdem.komunalka.controller.common;

import com.mdem.komunalka.model.User;
import com.mdem.komunalka.service.impl.RoleService;
import com.mdem.komunalka.service.impl.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
@Api(tags = {"Registration"}, description="Operation for users registration")
@PropertySource("classpath:messages.properties")
public class RegistrationController {

    private UserService userService;
    private RoleService roleService;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Value("${user.password.pattern}")
    private String USER_PASSWORD_PATTERN;

    @Autowired
    public RegistrationController(UserService userService, RoleService roleService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userService = userService;
        this.roleService = roleService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.CREATED)
    @ApiOperation(value = "Registration a new user")
    public void createUser(@Validated @RequestBody User user, BindingResult result) throws MethodArgumentNotValidException {

        String validationPattern = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,}$";

        Pattern pattern = Pattern.compile(validationPattern);
        Matcher matcher = pattern.matcher(user.getPassword());

        if (!matcher.matches()) {
            FieldError error = new FieldError("user","password", USER_PASSWORD_PATTERN);
            result.addError(error);
            throw new MethodArgumentNotValidException(null, result);
        }

        user.setAuthorities(Collections.singletonList(roleService.getRoleByName("user")));
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userService.create(user);
    }
}
