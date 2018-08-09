package com.mdem.komunalka.controller;

import com.mdem.komunalka.model.Tariff;
import com.mdem.komunalka.service.IAbstractService;
import com.mdem.komunalka.service.impl.CategoryService;
import com.mdem.komunalka.service.impl.TariffService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController("tariffController")
@RequestMapping("api/tariff")
@Api(tags = {"Tariff"}, description="Operations for work with tariffs on services")
public class TariffController {

    private TariffService tariffService;
    private CategoryService categoryService;

    @Autowired
    public TariffController(TariffService tariffService, CategoryService categoryService) {
        this.tariffService = tariffService;
        this.categoryService = categoryService;
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.CREATED)
    @PreAuthorize("hasRole('ADMIN') or (hasRole('USER') and #tariff.category.user.id == authentication.details.id)")
    @ApiOperation(value = "Add a new tariff")
    public void createTariff(@RequestBody Tariff tariff) {
        tariffService.create(tariff);
    }

    @RequestMapping(value = "/id/{id}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    @PreAuthorize("isAuthenticated()")
    @PostAuthorize("hasRole('ADMIN') or (hasRole('USER') and returnObject.category.user.id == authentication.details.id)")
    @ApiOperation(value = "Search a tariff with an ID", response = Tariff.class)
    public Tariff getTariffById(@PathVariable Long id) {
        return tariffService.getById(id);
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    @PreAuthorize("hasRole('ADMIN') or (hasRole('USER') and #tariff.category.user.id == authentication.details.id)")
    @ApiOperation(value = "Update an existing tariff")
    public void updateTariff(@RequestBody Tariff tariff) {
        tariffService.update(tariff);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    @PreAuthorize("hasRole('ADMIN') or (hasRole('USER') and #tariff.category.user.id == authentication.details.id)")
    @ApiOperation(value = "Delete an existing tariff")
    public void deleteTariff(@RequestBody Tariff tariff) {
        tariffService.delete(tariff);
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    @PreAuthorize("hasRole('ADMIN')")
    @ApiOperation(value = "View a list of available tariffs", response = Iterable.class)
    public List<Tariff> getAllTariffs() {
        return tariffService.getAll();
    }

    @RequestMapping(value = "/category/{categoryId}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    @PreAuthorize("isAuthenticated()")
    @PostAuthorize("hasRole('ADMIN') or (hasRole('USER') and @tariffController.getUserId(#categoryId) == authentication.details.id)")
    @ApiOperation(value = "View a list of available tariffs from selected category", response = Iterable.class)
    public List<Tariff> getTariffsByCategoryId(@PathVariable Long categoryId) {
        return tariffService.getTariffsByCategoryId(categoryId);
    }

    public Long getUserId(Long categoryId) {
        return categoryService.getById(categoryId).getUser().getId();
    }
}