package com.mdem.komunalka.controller;

import com.mdem.komunalka.model.Tariff;
import com.mdem.komunalka.service.IAbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("api/tariff")
public class TariffController {

    @Autowired
    private IAbstractService<Tariff, Long> tariffService;

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.CREATED)
    public void createTariff(@RequestBody Tariff tariff) {
        tariffService.create(tariff);
    }

    @RequestMapping(value = "/id/{id}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public Tariff getTariffById(@PathVariable Long id) {
        Tariff tariff = tariffService.getById(id);
        return tariff;
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void updateTariff(@RequestBody Tariff tariff) {
        tariffService.update(tariff);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteTariff(@RequestBody Tariff tariff) {
        tariffService.delete(tariff);
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public List<Tariff> getAllTariffs() throws IOException {
        List<Tariff> tariffs = tariffService.getAll();
        return tariffs;
    }
}