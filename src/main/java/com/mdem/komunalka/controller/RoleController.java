package com.mdem.komunalka.controller;

import com.mdem.komunalka.model.Role;
import com.mdem.komunalka.service.IAbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private IAbstractService<Role, Long> roleService;

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.CREATED)
    public void createRole(@RequestBody Role role) {
        roleService.create(role);
    }

    @RequestMapping(value = "/id/{id}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public Role getRoleById(@PathVariable Long id) {
        Role role = roleService.getById(id);
        return role;
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void updateRole(@RequestBody Role role) {
        roleService.update(role);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteRole(@RequestBody Role role) {
        roleService.delete(role);
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public List<Role> getAllRoles() throws IOException {
        List<Role> roles = roleService.getAll();
        return roles;
    }
}