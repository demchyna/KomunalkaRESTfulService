package com.mdem.komunalka.service;

import com.mdem.komunalka.DAO.CategoryDAO;
import com.mdem.komunalka.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryDAO categoryDAO;

    public List<Category> getAllCategories() {
        List<Category> categories = categoryDAO.getAllCategories();
        if (!categories.isEmpty()) {

            System.out.println("Is not empty!");

            return categories;
        } else {

            System.out.println("Is empty!");

            throw new RuntimeException("Resources not found");
        }
    }
}
