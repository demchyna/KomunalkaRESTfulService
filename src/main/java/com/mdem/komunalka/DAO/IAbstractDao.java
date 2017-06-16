package com.mdem.komunalka.DAO;

import java.io.Serializable;
import java.util.List;

public interface IAbstractDao<T, K extends Serializable> {
    void create(T entity);
    T getById(K id);
    void update(T entity);
    void delete(T entity);
    List<T> getAll();
}