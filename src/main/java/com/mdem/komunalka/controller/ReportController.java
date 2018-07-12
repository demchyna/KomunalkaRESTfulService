package com.mdem.komunalka.controller;

import com.mdem.komunalka.model.Report;
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
@RequestMapping("api/report")
@Api(tags = {"Report"}, description="Operations for work with user reports")
public class ReportController {

    private IAbstractService<Report, Long> reportService;

    @Autowired
    public ReportController(IAbstractService<Report, Long> reportService) {
        this.reportService = reportService;
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.CREATED)
    @PreAuthorize("isAuthenticated()")
    @ApiOperation(value = "Add a new report")
    public void createReport(@RequestBody Report report) {
        reportService.create(report);
    }

    @RequestMapping(value = "/id/{id}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    @ApiOperation(value = "Search a report with an ID", response = Report.class)
    public Report getReportById(@PathVariable Long id) {
        return reportService.getById(id);
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    @ApiOperation(value = "Update an existing report")
    public void updateReport(@RequestBody Report report) {
        reportService.update(report);
    }

    @RequestMapping(value = "/{id}/delete", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    @ApiOperation(value = "Delete an existing report")
    public void deleteReport(@PathVariable Long id) {
        reportService.delete(id);
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    @ApiOperation(value = "View a list of available reports", response = Iterable.class)
    public List<Report> getAllReports() {
        return reportService.getAll();
    }
}