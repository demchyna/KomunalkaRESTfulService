package com.mdem.komunalka.DAO.impl;

import com.mdem.komunalka.DAO.ICategoryDao;
import com.mdem.komunalka.DAO.common.AbstractDao;
import com.mdem.komunalka.model.Category;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CategoryDao extends AbstractDao<Category, Long> implements ICategoryDao {
    @Override
    public List<Category> getCategoryByUserId(long userId) {
        Query query = getSession().createQuery("FROM " + Category.class.getName() + " where user_id = :user_id");
        query.setParameter("user_id", userId);

        return (List<Category>) query.getResultList();
    }
}
