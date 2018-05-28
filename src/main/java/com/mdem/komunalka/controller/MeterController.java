package com.mdem.komunalka.controller;

import com.mdem.komunalka.model.Meter;
import com.mdem.komunalka.service.IAbstractService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("api/meter")
@Api(tags = {"Meter"}, description="Operations for work with meters")
public class MeterController {

    private IAbstractService<Meter, Long> meterService;

    @Autowired
    public MeterController(IAbstractService<Meter, Long> meterService) {
        this.meterService = meterService;
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.CREATED)
    @ApiOperation(value = "Add a new meter")
    public void createMeter(@RequestBody Meter meter) {
        meterService.create(meter);
    }

    @RequestMapping(value = "/id/{id}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    @PreAuthorize("isAuthenticated()")
    @ApiOperation(value = "Search a meter with an ID", response = Meter.class)
    public Meter getMeterById(@PathVariable Long id) {
        return meterService.getById(id);
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    @ApiOperation(value = "Update an existing meter")
    public void updateMeter(@RequestBody Meter meter) {
        meterService.update(meter);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    @ApiOperation(value = "Delete an existing meter")
    public void deleteMeter(@RequestBody Meter meter) {
        meterService.delete(meter);
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    @PreAuthorize("isAuthenticated()")
    @ApiOperation(value = "View a list of available meters", response = Iterable.class)
    public List<Meter> getAllMeters() throws IOException {
        return meterService.getAll();
    }
}