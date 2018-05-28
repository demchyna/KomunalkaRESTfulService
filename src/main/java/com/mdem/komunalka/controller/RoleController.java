package com.mdem.komunalka.controller;

import com.mdem.komunalka.model.Role;
import com.mdem.komunalka.service.IAbstractService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("api/role")
@Api(tags = {"Role"}, description="Operations for work with user roles")
public class RoleController {

    private IAbstractService<Role, Long> roleService;

    @Autowired
    public RoleController(IAbstractService<Role, Long> roleService) {
        this.roleService = roleService;
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.CREATED)
    @ApiOperation(value = "Add a new role")
    public void createRole(@RequestBody Role role) {
        roleService.create(role);
    }

    @RequestMapping(value = "/id/{id}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    @ApiOperation(value = "Search a role with an ID", response = Role.class)
    public Role getRoleById(@PathVariable Long id) {
        return roleService.getById(id);
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    @ApiOperation(value = "Update an existing role")
    public void updateRole(@RequestBody Role role) {
        roleService.update(role);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    @ApiOperation(value = "Delete an existing role")
    public void deleteRole(@RequestBody Role role) {
        roleService.delete(role);
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    @ApiOperation(value = "View a list of available roles", response = Iterable.class)
    public List<Role> getAllRoles() throws IOException {
        return roleService.getAll();
    }
}