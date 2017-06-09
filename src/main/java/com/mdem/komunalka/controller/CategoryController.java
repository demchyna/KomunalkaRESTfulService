package com.mdem.komunalka.controller;

import com.mdem.komunalka.model.Category;
import com.mdem.komunalka.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @RequestMapping(value = "/id/{id}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public Category getCategoryById(@PathVariable Long id) {
        Category category = categoryService.getCategoryById(id);
        return category;
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public List<Category> getAllCategory() throws IOException {
        List<Category> categories = categoryService.getAllCategories();
        return categories;
    }
}