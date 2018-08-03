package com.mdem.komunalka.service;

import com.mdem.komunalka.model.Indicator;

import java.util.List;

public interface IIndicatorService {
    List<Indicator> getIndicatorsByMeterId(long meterId);
    Indicator getLastAddedIndicatorByMeterId(long meterId);
}
