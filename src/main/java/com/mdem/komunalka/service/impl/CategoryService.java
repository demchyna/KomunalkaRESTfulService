package com.mdem.komunalka.service.impl;

import com.mdem.komunalka.DAO.impl.CategoryDao;
import com.mdem.komunalka.model.Category;
import com.mdem.komunalka.service.ICategoryService;
import com.mdem.komunalka.service.common.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class CategoryService extends AbstractService<Category, Long> implements ICategoryService {

    private CategoryDao categoryDao;

    @Autowired
    public void setCategoryDao(CategoryDao categoryDao) {
        this.categoryDao = categoryDao;
    }

    @Override
    @Transactional
    public List<Category> getCategoryByUserId(long userId) {
        return categoryDao.getCategoryByUserId(userId);
    }
}