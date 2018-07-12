package com.mdem.komunalka.DAO;

import com.mdem.komunalka.model.Meter;

import java.util.List;

public interface IMeterDao {
    List<Meter> getMetersByCategoryIdAndUserId(long categoryId, long userId);
}
