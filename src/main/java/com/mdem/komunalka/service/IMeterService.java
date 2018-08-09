package com.mdem.komunalka.service;

import com.mdem.komunalka.model.Meter;

import java.util.List;

public interface IMeterService {
    List<Meter> getMetersByCategoryId(long categoryId);
}
