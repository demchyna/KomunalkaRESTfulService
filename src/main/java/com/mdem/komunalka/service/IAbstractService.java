package com.mdem.komunalka.service;

import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

public interface IAbstractService<T, K extends Serializable> {
    void create(T entity) throws Exception;
    T getById(K id) throws Exception;
    void update(T entity, K id) throws Exception;
    void delete(T entity, K id) throws Exception;
    List<T> getAll();
}
