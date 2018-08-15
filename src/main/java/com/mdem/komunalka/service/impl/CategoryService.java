package com.mdem.komunalka.service.impl;

import com.mdem.komunalka.DAO.impl.CategoryDao;
import com.mdem.komunalka.DAO.impl.UserDao;
import com.mdem.komunalka.exception.DataNotFoundException;
import com.mdem.komunalka.model.Category;
import com.mdem.komunalka.model.User;
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
    private UserDao userDao;

    @Autowired
    public void setCategoryDao(CategoryDao categoryDao) {
        this.categoryDao = categoryDao;
    }

    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    @Transactional
    public List<Category> getCategoryByUserId(long userId) {
        User user = userDao.getById(userId);
        if (user == null) {
            throw new DataNotFoundException("Record with id " + userId  +" not found in database");
        }
        return categoryDao.getCategoryByUserId(userId);
    }
}