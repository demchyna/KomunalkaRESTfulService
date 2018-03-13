package com.mdem.komunalka.controller;

import com.mdem.komunalka.model.Unit;
import com.mdem.komunalka.service.IAbstractService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("api/unit")
@Api(tags = {"Unit"}, description="Operations for work with units of measurement")
public class UnitController {

    @Autowired
    private IAbstractService<Unit, Long> unitService;

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.CREATED)
    public void createUnit(@RequestBody Unit unit) {
        unitService.create(unit);
    }

    @RequestMapping(value = "/id/{id}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public Unit getUnitById(@PathVariable Long id) {
        Unit unit = unitService.getById(id);
        return unit;
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void updateUnit(@RequestBody Unit unit) {
        unitService.update(unit);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteUnit(@RequestBody Unit unit) {
        unitService.delete(unit);
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public List<Unit> getAllUnits() throws IOException {
        List<Unit> units = unitService.getAll();
        return units;
    }
}