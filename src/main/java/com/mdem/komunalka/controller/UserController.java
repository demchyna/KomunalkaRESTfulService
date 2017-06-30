package com.mdem.komunalka.controller;

import com.mdem.komunalka.model.User;
import com.mdem.komunalka.service.IAbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IAbstractService<User, Long> userService;

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.CREATED)
    public void createUser(@RequestBody User user) {
        userService.create(user);
    }

    @RequestMapping(value = "/id/{id}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public User getUserById(@PathVariable Long id) {
        User user = userService.getById(id);
        return user;
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void updateUser(@RequestBody User user) {
        userService.update(user);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteUser(@RequestBody User user) {
        userService.delete(user);
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public List<User> getAllUsers() throws IOException {
        List<User> users = userService.getAll();
        return users;
    }
}