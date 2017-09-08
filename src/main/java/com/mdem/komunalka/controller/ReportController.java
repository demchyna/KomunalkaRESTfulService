package com.mdem.komunalka.controller;

import com.mdem.komunalka.model.Report;
import com.mdem.komunalka.service.IAbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("api/report")
public class ReportController {

    @Autowired
    private IAbstractService<Report, Long> reportService;

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.CREATED)
    public void createReport(@RequestBody Report report) {
        reportService.create(report);
    }

    @RequestMapping(value = "/id/{id}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public Report getReportById(@PathVariable Long id) {
        Report report = reportService.getById(id);
        return report;
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void updateReport(@RequestBody Report report) {
        reportService.update(report);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteReport(@RequestBody Report report) {
        reportService.delete(report);
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public List<Report> getAllReports() throws IOException {
        List<Report> reports = reportService.getAll();
        return reports;
    }
}