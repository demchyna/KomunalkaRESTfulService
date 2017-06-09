package com.mdem.komunalka.DAO;

import java.sql.SQLException;
import java.util.List;

public interface IGenericDao <T> {
    void create(T entity);
    T getById(Long id);
    void update(T entity);
    void delete(T entity);
    List<T> getAll();
}