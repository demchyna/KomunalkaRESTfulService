package com.mdem.komunalka.controller;

import com.mdem.komunalka.DTO.IndicatorDto;
import com.mdem.komunalka.model.Indicator;
import com.mdem.komunalka.model.Meter;
import com.mdem.komunalka.service.impl.IndicatorService;
import com.mdem.komunalka.service.impl.MeterService;
import com.mdem.komunalka.transformer.IndicatorTransformer;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/indicator")
@Api(tags = {"Indicator"}, description="Operations for work with indicators of meters")
public class IndicatorController {

    private IndicatorService indicatorService;
    private MeterService meterService;

    @Autowired
    public IndicatorController(IndicatorService indicatorService, MeterService meterService) {
        this.indicatorService = indicatorService;
        this.meterService = meterService;
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.CREATED)
    @ApiOperation(value = "Add a new indicator")
    public void createIndicator(@RequestBody IndicatorDto indicatorDto) {
        Meter meter = meterService.getById(indicatorDto.getMeterId());
        Indicator indicator = IndicatorTransformer.toIndicatorEntity(indicatorDto, meter);
        indicatorService.create(indicator);
    }

    @RequestMapping(value = "/id/{id}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    @PreAuthorize("isAuthenticated()")
    @ApiOperation(value = "Search a indicator with an ID", response = Indicator.class)
    public IndicatorDto getIndicatorById(@PathVariable Long id) {
        Indicator currentIndicator = indicatorService.getById(id);
        Long previousIndicator = 0L;
        if (currentIndicator.getPreviousId() != null) {
            previousIndicator = indicatorService.getById(currentIndicator.getPreviousId()).getCurrent();
        }
        return IndicatorTransformer.toIndicatorDto(currentIndicator, previousIndicator);
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    @ApiOperation(value = "Update an existing indicator")
    public void updateIndicator(@RequestBody IndicatorDto indicatorDto) {
        Meter meter = meterService.getById(indicatorDto.getMeterId());
        Indicator indicator = IndicatorTransformer.toIndicatorEntity(indicatorDto, meter);
        indicatorService.update(indicator);
    }

    @RequestMapping(value = "/{id}/delete", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    @ApiOperation(value = "Delete an existing indicator")
    public void deleteIndicator(@PathVariable Long id) {
        indicatorService.delete(id);
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    @PreAuthorize("isAuthenticated()")
    @ApiOperation(value = "View a list of available indicators", response = Iterable.class)
    public List<IndicatorDto> getAllIndicators() {
        List<Indicator> indicators = indicatorService.getAll();
        List<IndicatorDto> indicatorsDto = new ArrayList<>();
        Long previousIndicator = 0L;
        for (Indicator currentIndicator : indicators) {
            if (currentIndicator.getPreviousId() != null) {
                previousIndicator = indicatorService.getById(currentIndicator.getPreviousId()).getCurrent();
            }
            IndicatorDto indicatorDto = IndicatorTransformer.toIndicatorDto(currentIndicator, previousIndicator);
            indicatorsDto.add(indicatorDto);
        }
        return indicatorsDto;
    }

    @RequestMapping(value = "/meter/{meterId}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    @PreAuthorize("isAuthenticated()")
    @ApiOperation(value = "View a list of available indicators for selected meter", response = Iterable.class)
    public List<IndicatorDto> getIndicatorsByMeterId(@PathVariable Long meterId) {
        List<Indicator> indicators = indicatorService.getIndicatorsByMeterId(meterId);
        List<IndicatorDto> indicatorsDto = new ArrayList<>();
        Long previousIndicator = 0L;
        for (Indicator currentIndicator : indicators) {
            if (currentIndicator.getPreviousId() != null) {
                previousIndicator = indicatorService.getById(currentIndicator.getPreviousId()).getCurrent();
            }
            IndicatorDto indicatorDto = IndicatorTransformer.toIndicatorDto(currentIndicator, previousIndicator);
            indicatorsDto.add(indicatorDto);
        }
        return indicatorsDto;
    }
}
