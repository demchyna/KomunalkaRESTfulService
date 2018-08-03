package com.mdem.komunalka.service.impl;

import com.mdem.komunalka.DAO.impl.TariffDao;
import com.mdem.komunalka.model.Tariff;
import com.mdem.komunalka.service.ITariffService;
import com.mdem.komunalka.service.common.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class TariffService extends AbstractService<Tariff, Long> implements ITariffService {

    private TariffDao tariffDao;

    @Autowired
    public void setTariffDao(TariffDao tariffDao) {
        this.tariffDao = tariffDao;
    }

    @Override
    @Transactional
    public List<Tariff> getTariffsByCategoryId(long categoryId) {
        return tariffDao.getTariffsByCategoryId(categoryId);
    }
}
