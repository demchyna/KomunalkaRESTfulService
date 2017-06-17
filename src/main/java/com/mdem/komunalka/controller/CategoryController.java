package com.mdem.komunalka.controller;

import com.mdem.komunalka.model.Category;
import com.mdem.komunalka.service.IAbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private IAbstractService<Category, Long> categoryService;

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.CREATED)
    public void createCategory(@RequestBody Category category) {
        categoryService.create(category);
    }

    @RequestMapping(value = "/id/{id}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public Category getCategoryById(@PathVariable Long id) {
        Category category = categoryService.getById(id);
        return category;
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void updateCategory(@RequestBody Category category) {
        categoryService.update(category);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteCategory(@RequestBody Category category) {
        categoryService.delete(category);
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public List<Category> getAllCategory() throws IOException {
        List<Category> categories = categoryService.getAll();
        return categories;
    }
}