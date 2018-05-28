package com.mdem.komunalka.controller;

import com.mdem.komunalka.model.Tariff;
import com.mdem.komunalka.service.IAbstractService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("api/tariff")
@Api(tags = {"Tariff"}, description="Operations for work with tariffs on services")
public class TariffController {

    private IAbstractService<Tariff, Long> tariffService;

    @Autowired
    public TariffController(IAbstractService<Tariff, Long> tariffService) {
        this.tariffService = tariffService;
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.CREATED)
    @ApiOperation(value = "Add a new tariff")
    public void createTariff(@RequestBody Tariff tariff) {
        tariffService.create(tariff);
    }

    @RequestMapping(value = "/id/{id}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    @ApiOperation(value = "Search a tariff with an ID", response = Tariff.class)
    public Tariff getTariffById(@PathVariable Long id) {
        return tariffService.getById(id);
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    @ApiOperation(value = "Update an existing tariff")
    public void updateTariff(@RequestBody Tariff tariff) {
        tariffService.update(tariff);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    @ApiOperation(value = "Delete an existing tariff")
    public void deleteTariff(@RequestBody Tariff tariff) {
        tariffService.delete(tariff);
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    @ApiOperation(value = "View a list of available tariffs", response = Iterable.class)
    public List<Tariff> getAllTariffs() throws IOException {
        return tariffService.getAll();
    }
}