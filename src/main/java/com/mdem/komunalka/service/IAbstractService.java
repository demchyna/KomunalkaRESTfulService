package com.mdem.komunalka.service;

import com.mdem.komunalka.exception.ConflictDataException;
import com.mdem.komunalka.exception.DataNotFoundException;
import com.mdem.komunalka.exception.NoDataException;
import com.mdem.komunalka.model.User;
import com.mdem.komunalka.model.common.IEntity;

import java.io.Serializable;
import java.util.List;

public interface IAbstractService<T extends IEntity, K extends Serializable> {
    void create(T entity) throws ConflictDataException;
    T getById(K id) throws DataNotFoundException;
    void update(T entity) throws DataNotFoundException;
    void delete(T entity) throws DataNotFoundException;
    List<T> getAll() throws NoDataException;
}
