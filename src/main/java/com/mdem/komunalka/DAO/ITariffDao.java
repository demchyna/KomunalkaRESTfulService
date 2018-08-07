package com.mdem.komunalka.DAO;

import com.mdem.komunalka.model.Tariff;

import java.util.List;

public interface ITariffDao {
    List<Tariff> getTariffsByCategoryId(long categoryId);

}
