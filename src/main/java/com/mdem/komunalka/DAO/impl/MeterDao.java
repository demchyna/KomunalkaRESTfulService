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
    public List<Meter> getMetersByCategoryId(long categoryId) {
        Query query = getSession().createQuery("FROM " + Meter.class.getName() + " where category_id = :category_id");
        query.setParameter("category_id", categoryId);

        return (List<Meter>) query.getResultList();
    }
}
