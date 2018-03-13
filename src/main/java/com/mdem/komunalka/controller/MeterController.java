package com.mdem.komunalka.controller;

import com.mdem.komunalka.model.Meter;
import com.mdem.komunalka.service.IAbstractService;
import io.swagger.annotations.Api;
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

    @Autowired
    private IAbstractService<Meter, Long> meterService;

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.CREATED)
    public void createMeter(@RequestBody Meter meter) {
        meterService.create(meter);
    }

    @RequestMapping(value = "/id/{id}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    @PreAuthorize("isAuthenticated()")
    public Meter getMeterById(@PathVariable Long id) {
        Meter meter = meterService.getById(id);
        return meter;
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void updateMeter(@RequestBody Meter meter) {
        meterService.update(meter);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteMeter(@RequestBody Meter meter) {
        meterService.delete(meter);
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    @PreAuthorize("isAuthenticated()")
    public List<Meter> getAllMeters() throws IOException {
        List<Meter> meters = meterService.getAll();
        return meters;
    }
}