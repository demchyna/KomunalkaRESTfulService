package com.mdem.komunalka.controller;

import com.mdem.komunalka.model.Category;
import com.mdem.komunalka.service.impl.CategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("api/category")
@Api(tags = {"Category"}, description="Operations for work with categories of services")
public class CategoryController {

    private CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.CREATED)
    @PreAuthorize("hasRole('ADMIN') or (hasRole('USER') and #category.user.id == authentication.details.id)")
    @ApiOperation(value = "Add a new category")
    public void createCategory(@Validated @RequestBody Category category) {
        categoryService.create(category);
    }

    @RequestMapping(value = "/id/{id}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    @PreAuthorize("isAuthenticated()")
    @PostAuthorize("hasRole('ADMIN') or (hasRole('USER') and returnObject.user.id == authentication.details.id)")
    @ApiOperation(value = "Search a category with an ID", response = Category.class)
    public Category getCategoryById(@PathVariable Long id) {
        return categoryService.getById(id);
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    @PreAuthorize("hasRole('ADMIN') or (hasRole('USER') and #category.user.id == authentication.details.id)")
    @ApiOperation(value = "Update an existing category")
    public void updateCategory(@Validated @RequestBody Category category) {
        categoryService.update(category);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    @PreAuthorize("hasRole('ADMIN') or (hasRole('USER') and #category.user.id == authentication.details.id)")
    @ApiOperation(value = "Delete an existing category")
    public void deleteCategory(@Validated @RequestBody Category category) {
        categoryService.delete(category);
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    @PreAuthorize("hasRole('ADMIN')")
    @ApiOperation(value = "View a list of available categories", response = Iterable.class)
    public List<Category> getAllCategories() {
        return categoryService.getAll();
    }

    @RequestMapping(value = "/user/{userId}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    @PreAuthorize("isAuthenticated()")
    @PostAuthorize("hasRole('ADMIN') or (hasRole('USER') and #userId == authentication.details.id)")
    @ApiOperation(value = "View a list of available categories for selected user", response = Iterable.class)
    public List<Category> getCategoriesByUserId(@PathVariable Long userId) {
        return categoryService.getCategoryByUserId(userId);
    }
}