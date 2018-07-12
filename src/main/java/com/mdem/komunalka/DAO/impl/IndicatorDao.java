package com.mdem.komunalka.DAO.impl;

import com.mdem.komunalka.DAO.IIndicatorDao;
import com.mdem.komunalka.DAO.common.AbstractDao;
import com.mdem.komunalka.model.Indicator;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class IndicatorDao extends AbstractDao<Indicator, Long> implements IIndicatorDao {

    @Override
    public List<Indicator> getIndicatorsByMeterId(long meterId) {
        Query query = getSession().createQuery("FROM " + Indicator.class.getName() + " where meter_id = :meter_id");
        query.setParameter("meter_id", meterId);

        return (List<Indicator>) query.getResultList();
    }
}
