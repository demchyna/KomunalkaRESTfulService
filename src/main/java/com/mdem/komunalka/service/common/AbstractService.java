package com.mdem.komunalka.service.common;

import com.mdem.komunalka.DAO.IAbstractDao;
import com.mdem.komunalka.model.IEntity;
import com.mdem.komunalka.service.IAbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

@Service
public abstract class AbstractService<T extends IEntity, K extends Serializable> implements IAbstractService<T, K> {

    @Autowired
    private IAbstractDao<T, K> abstractDao;

    @Override
    @Transactional
    public void create(T entity) throws Exception {
        if (entity != null) {
            abstractDao.create(entity);
        } else {
            throw new Exception("Record cannot be added in database");
        }

    }

    @Override
    @Transactional
    public T getById(K id) throws Exception {
        T entity = abstractDao.getById(id);
        if (entity != null) {
            return entity;
        } else {
            throw new Exception("Record with id " + id  +" not found in database");
        }
    }

    @Override
    @Transactional
    public void update(T entity) throws Exception {
        if (entity != null) {
            abstractDao.update(entity);
        } else {
            throw new Exception("Entity is NULL");
        }
    }

    @Override
    @Transactional
    public void delete(T entity) throws Exception {
        if (entity != null) {
            T delEntity = abstractDao.getById((K) entity.getId());
            if (delEntity != null) {
                abstractDao.delete(entity);
            } else {
                throw new Exception("Record with id " + entity.getId()  +" not found in database");
            }
        } else {
            throw new Exception("Entity is NULL");
        }
    }

    @Override
    @Transactional
    public List getAll() {
        List<T> categories = abstractDao.getAll();
        if (!categories.isEmpty()) {
            return categories;
        } else {
            return null;
        }
    }
}
