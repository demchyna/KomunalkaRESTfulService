package com.mdem.komunalka.DAO;

import com.mdem.komunalka.model.Indicator;

import java.util.List;

public interface IIndicatorDao {
    List<Indicator> getIndicatorsByMeterId(long meterId);
}
