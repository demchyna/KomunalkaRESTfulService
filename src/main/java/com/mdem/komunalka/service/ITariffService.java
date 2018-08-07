package com.mdem.komunalka.service;

import com.mdem.komunalka.model.Tariff;

import java.util.List;

public interface ITariffService {
    List<Tariff> getTariffsByCategoryId(long categoryId);
}
