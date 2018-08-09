package com.mdem.komunalka.service.impl;

import com.mdem.komunalka.DAO.impl.MeterDao;
import com.mdem.komunalka.exception.NoDataException;
import com.mdem.komunalka.model.Meter;
import com.mdem.komunalka.service.IMeterService;
import com.mdem.komunalka.service.common.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class MeterService extends AbstractService<Meter, Long> implements IMeterService {

    private MeterDao meterDao;

    @Autowired
    public void setMeterDao(MeterDao meterDao) {
        this.meterDao = meterDao;
    }

    @Override
    @Transactional
    public List<Meter> getMetersByCategoryId(long categoryId) {
        return meterDao.getMetersByCategoryId(categoryId);
    }
}
