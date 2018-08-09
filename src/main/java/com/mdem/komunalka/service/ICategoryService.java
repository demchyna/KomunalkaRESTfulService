package com.mdem.komunalka.service;

import com.mdem.komunalka.model.Category;

import java.util.List;

public interface ICategoryService {
    List<Category> getCategoryByUserId(long userId);
}
