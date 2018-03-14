package com.mdem.komunalka.controller;

import com.mdem.komunalka.model.Indicator;
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
@RequestMapping("api/indicator")
@Api(tags = {"Indicator"}, description="Operations for work with indicators of meters")
public class IndicatorController {
    @Autowired
    private IAbstractService<Indicator, Long> indicatorService;

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.CREATED)
    @ApiOperation(value = "Add a new indicator")
    public void createIndicator(@RequestBody Indicator indicator) {
        indicatorService.create(indicator);
    }

    @RequestMapping(value = "/id/{id}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    @PreAuthorize("isAuthenticated()")
    @ApiOperation(value = "Search a indicator with an ID", response = Indicator.class)
    public Indicator getIndicatorById(@PathVariable Long id) {
        Indicator indicator = indicatorService.getById(id);
        return indicator;
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    @ApiOperation(value = "Update an existing indicator")
    public void updateIndicator(@RequestBody Indicator indicator) {
        indicatorService.update(indicator);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    @ApiOperation(value = "Delete an existing indicator")
    public void deleteIndicator(@RequestBody Indicator indicator) {
        indicatorService.delete(indicator);
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    @PreAuthorize("isAuthenticated()")
    @ApiOperation(value = "View a list of available indicators", response = Iterable.class)
    public List<Indicator> getAllIndicators() throws IOException {
        List<Indicator> indicators = indicatorService.getAll();
        return indicators;
    }
}
