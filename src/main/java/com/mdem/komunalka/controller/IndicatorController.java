package com.mdem.komunalka.controller;

import com.mdem.komunalka.model.Indicator;
import com.mdem.komunalka.service.IAbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/indicator")
public class IndicatorController {
    @Autowired
    private IAbstractService<Indicator, Long> indicatorService;

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.CREATED)
    public void createIndicator(@RequestBody Indicator indicator) {
        indicatorService.create(indicator);
    }

    @RequestMapping(value = "/id/{id}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public Indicator getIndicatorById(@PathVariable Long id) {
        Indicator indicator = indicatorService.getById(id);
        return indicator;
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void updateIndicator(@RequestBody Indicator indicator) {
        indicatorService.update(indicator);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteIndicator(@RequestBody Indicator indicator) {
        indicatorService.delete(indicator);
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public List<Indicator> getAllIndicators() throws IOException {
        List<Indicator> indicators = indicatorService.getAll();
        return indicators;
    }
}