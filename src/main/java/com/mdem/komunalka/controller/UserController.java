package com.mdem.komunalka.controller;

import com.mdem.komunalka.model.User;
import com.mdem.komunalka.service.impl.UserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("api/user")
@Api(tags = {"User"}, description="Operations for work with users")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/id/{id}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    @PreAuthorize("hasRole('ADMIN') or (hasRole('USER') and #id == authentication.details.id)")
    public User getUserById(@PathVariable Long id) {
        User user = userService.getById(id);
        return user;
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    @PreAuthorize("hasRole('ADMIN') or (hasRole('USER') and #user.id == authentication.details.id)")
    public void updateUser(@RequestBody User user) {
        userService.update(user);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    @PreAuthorize("hasRole('ADMIN') or (hasRole('USER') and #user.id == authentication.details.id)")
    public void deleteUser(@RequestBody User user) {
        userService.delete(user);
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    @PreAuthorize("hasRole('ADMIN')")
    public List<User> getAllUsers() throws IOException {
        List<User> users = userService.getAll();
        return users;
    }

    @RequestMapping(value = "/login/{login}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public User getUserByLogin(@PathVariable String login) {
        User user = (User) userService.loadUserByUsername(login);
        return user;
    }
}