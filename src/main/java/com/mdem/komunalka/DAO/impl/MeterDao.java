package com.mdem.komunalka.DAO.impl;

import com.mdem.komunalka.DAO.IMeterDao;
import com.mdem.komunalka.DAO.common.AbstractDao;
import com.mdem.komunalka.model.Meter;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MeterDao extends AbstractDao<Meter, Long> implements IMeterDao {

    @Override
    public List<Meter> getMetersByCategoryIdAndUserId(long categoryId, long userId) {
        Query query = getSession().createQuery("FROM " + Meter.class.getName() + " where category_id = :category_id and user_id = :user_id");
        query.setParameter("category_id", categoryId);
        query.setParameter("user_id", userId);

        return (List<Meter>) query.getResultList();
    }
}
