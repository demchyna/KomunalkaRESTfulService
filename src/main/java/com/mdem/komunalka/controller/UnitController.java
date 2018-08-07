package com.mdem.komunalka.controller;

import com.mdem.komunalka.model.Unit;
import com.mdem.komunalka.service.IAbstractService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("api/unit")
@Api(tags = {"Unit"}, description="Operations for work with units of measurement")
public class UnitController {

    private IAbstractService<Unit, Long> unitService;

    @Autowired
    public UnitController(IAbstractService<Unit, Long> unitService) {
        this.unitService = unitService;
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.CREATED)
    @ApiOperation(value = "Add a new unit")
    public void createUnit(@RequestBody Unit unit) {
        unitService.create(unit);
    }

    @RequestMapping(value = "/id/{id}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    @ApiOperation(value = "Search a unit with an ID", response = Unit.class)
    public Unit getUnitById(@PathVariable Long id) {
        return unitService.getById(id);
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    @ApiOperation(value = "Update an existing unit")
    public void updateUnit(@RequestBody Unit unit) {
        unitService.update(unit);
    }

    @RequestMapping(value = "/{id}/delete", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    @ApiOperation(value = "Delete an existing unit")
    public void deleteUnit(@PathVariable Long id) {
        unitService.delete(id);
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    @ApiOperation(value = "View a list of available units", response = Iterable.class)
    public List<Unit> getAllUnits() {
        return unitService.getAll();
    }
}