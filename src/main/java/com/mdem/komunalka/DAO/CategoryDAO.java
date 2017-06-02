package com.mdem.komunalka.DAO;

import com.mdem.komunalka.model.Category;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;

@Repository
public class CategoryDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Transactional
    public void addCategory(Category category) {
        sessionFactory.getCurrentSession().save(category);
    }

    @Transactional
    public Category getCategoryById(int id) {
        Category category = sessionFactory.getCurrentSession().get(Category.class, id);
        if (category != null) {
            return category;
        } else {
            return null;
        }
    }

    @Transactional
    public void updateCategory(Category newCategory) throws SQLException {
        Category oldCategory = sessionFactory.getCurrentSession().get(Category.class, newCategory.getId());
        if (oldCategory != null) {
            sessionFactory.getCurrentSession().update(newCategory);
        } else {
            throw new SQLException();
        }
    }

    @Transactional
    public void deleteCategory(int id) throws SQLException {
        Category category = sessionFactory.getCurrentSession().get(Category.class, id);
        if (category != null) {
            sessionFactory.getCurrentSession().delete(category);
        } else {
            throw new SQLException();
        }
    }

    @Transactional
    public List<Category> getAllCategories() {
        @SuppressWarnings("unchecked")
        List<Category> categories = sessionFactory.getCurrentSession().createQuery("FROM Category").list();
        if (!categories.isEmpty()) {
            return categories;
        } else {
            return null;
        }
    }
}
