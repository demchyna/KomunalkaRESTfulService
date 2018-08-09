package com.mdem.komunalka.DAO;

import com.mdem.komunalka.model.Category;

import java.util.List;

public interface ICategoryDao {
    List<Category> getCategoryByUserId(long userId);

}
