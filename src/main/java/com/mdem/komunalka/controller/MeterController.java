package com.mdem.komunalka.controller;

import com.mdem.komunalka.model.Meter;
import com.mdem.komunalka.service.IAbstractService;
import com.mdem.komunalka.service.impl.CategoryService;
import com.mdem.komunalka.service.impl.MeterService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("meterController")
@RequestMapping("api/meter")
@Api(tags = {"Meter"}, description="Operations for work with meters")
public class MeterController {

    private MeterService meterService;
    private CategoryService categoryService;

    @Autowired
    public MeterController(MeterService meterService, CategoryService categoryService) {
        this.meterService = meterService;
        this.categoryService = categoryService;
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.CREATED)
    @PreAuthorize("hasRole('ADMIN') or (hasRole('USER') and #meter.category.user.id == authentication.details.id)")
    @ApiOperation(value = "Add a new meter")
    public void createMeter(@RequestBody Meter meter) {
        meterService.create(meter);
    }

    @RequestMapping(value = "/id/{id}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    @PreAuthorize("isAuthenticated()")
    @PostAuthorize("hasRole('ADMIN') or (hasRole('USER') and returnObject.category.user.id == authentication.details.id)")
    @ApiOperation(value = "Search a meter with an ID", response = Meter.class)
    public Meter getMeterById(@PathVariable Long id) {
        return meterService.getById(id);
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    @PreAuthorize("hasRole('ADMIN') or (hasRole('USER') and #meter.category.user.id == authentication.details.id)")
    @ApiOperation(value = "Update an existing meter")
    public void updateMeter(@RequestBody Meter meter) {
        meterService.update(meter);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    @PreAuthorize("hasRole('ADMIN') or (hasRole('USER') and #meter.category.user.id == authentication.details.id)")
    @ApiOperation(value = "Delete an existing meter")
    public void deleteMeter(@RequestBody Meter meter) {
        meterService.delete(meter);
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    @PreAuthorize("hasRole('ADMIN')")
    @ApiOperation(value = "View a list of available meters", response = Iterable.class)
    public List<Meter> getAllMeters() {
        return meterService.getAll();
    }

    @RequestMapping(value = "/category/{categoryId}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    @PreAuthorize("isAuthenticated()")
    @PostAuthorize("hasRole('ADMIN') or (hasRole('USER') and @meterController.getUserId(#categoryId) == authentication.details.id)")
    @ApiOperation(value = "View a list of available meters from selected category", response = Iterable.class)
    public List<Meter> getMetersByCategoryIdAndUserId(@PathVariable Long categoryId) {
        return meterService.getMetersByCategoryId(categoryId);
    }

    public Long getUserId(Long categoryId) {
        return categoryService.getById(categoryId).getUser().getId();
    }
}