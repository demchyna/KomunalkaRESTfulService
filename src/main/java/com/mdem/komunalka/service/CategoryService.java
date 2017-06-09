package com.mdem.komunalka.service;

import com.mdem.komunalka.DAO.impl.CategoryDaoImpl;
import com.mdem.komunalka.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryDaoImpl categoryDao;

    @Transactional
    public Category getCategoryById(Long id) {
        Category category = (Category) categoryDao.getById(id);
        return category;
    }

    @Transactional
    public List<Category> getAllCategories() {
        List<Category> categories = categoryDao.getAll();
        return categories;
    }
}
