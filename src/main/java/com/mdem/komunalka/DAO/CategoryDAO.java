package com.mdem.komunalka.DAO;

import com.mdem.komunalka.model.Category;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class CategoryDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Transactional
    public List<Category> getAllCategories() {
        @SuppressWarnings("unchecked")
        List<Category> categories = sessionFactory.getCurrentSession().createQuery("FROM Category").list();
        if (!categories.isEmpty()) {

            System.out.println("Is not empty!");

            return categories;
        }

        System.out.println("Is empty!");

        return null;
    }
}
