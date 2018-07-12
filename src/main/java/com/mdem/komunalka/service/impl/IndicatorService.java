package com.mdem.komunalka.service.impl;

import com.mdem.komunalka.DAO.impl.IndicatorDao;
import com.mdem.komunalka.model.Indicator;
import com.mdem.komunalka.service.IIndicatorService;
import com.mdem.komunalka.service.common.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class IndicatorService extends AbstractService<Indicator, Long> implements IIndicatorService {

    private IndicatorDao indicatorDao;

    @Autowired
    public void setIndicatorDao(IndicatorDao indicatorDao) {
        this.indicatorDao = indicatorDao;
    }

    @Override
    @Transactional
    public List<Indicator> getIndicatorsByMeterId(long meterId) {
        return indicatorDao.getIndicatorsByMeterId(meterId);
    }
}
