package com.mdem.komunalka.service;

import com.mdem.komunalka.model.IEntity;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

public interface IAbstractService<T extends IEntity, K extends Serializable> {
    void create(T entity) throws Exception;
    T getById(K id) throws Exception;
    void update(T entity) throws Exception;
    void delete(T entity) throws Exception;
    List<T> getAll();
}
