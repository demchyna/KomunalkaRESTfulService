package com.mdem.komunalka.service.common;

import com.mdem.komunalka.DAO.IAbstractDao;
import com.mdem.komunalka.exception.ConflictDataException;
import com.mdem.komunalka.exception.DataNotFoundException;
import com.mdem.komunalka.exception.NoDataException;
import com.mdem.komunalka.model.common.IEntity;
import com.mdem.komunalka.service.IAbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

public abstract class AbstractService<T extends IEntity, K extends Serializable> implements IAbstractService<T, K> {

    @Autowired
    private IAbstractDao<T, K> abstractDao;

    private Class<T> entityType;

    public AbstractService() {
        ParameterizedType genericSuperclass = (ParameterizedType) getClass().getGenericSuperclass();
        this.entityType = (Class<T>) genericSuperclass.getActualTypeArguments()[0];
    }

    @Override
    @Transactional
    public void create(T entity) throws ConflictDataException {
        if (((T) entity).getId() != null) {
            T extEntity = abstractDao.getById((K) entity.getId());
            if (extEntity == null) {
                abstractDao.create(entity);
            } else {
                throw new ConflictDataException("Record already exists in database");
            }
        } else {
            abstractDao.create(entity);
        }
    }

    @Override
    @Transactional
    public T getById(K id) throws DataNotFoundException {
        T entity = abstractDao.getById(id);
        if (entity != null) {
            return entity;
        } else {
            throw new DataNotFoundException("Record with id " + id  +" not found in database");
        }
    }

    @Override
    @Transactional
    public void update(T entity) throws DataNotFoundException {
        T updEntity = abstractDao.getById((K) entity.getId());
        if (updEntity != null) {
            abstractDao.update(entity);
        } else {
            throw new DataNotFoundException("Record with id " + entity.getId()  +" not found in database");
        }
    }

    @Override
    @Transactional
    public void delete(T entity) throws DataNotFoundException {
        T delEntity = abstractDao.getById((K) entity.getId());
        if (delEntity != null) {
            abstractDao.delete(delEntity);
        } else {
            throw new DataNotFoundException("Record with id " + entity.getId()  +" not found in database");
        }
    }

    @Override
    @Transactional
    public List getAll() throws NoDataException {
        List<T> categories = abstractDao.getAll();
        if (!categories.isEmpty()) {
            return categories;
        } else {
            throw new NoDataException("Table '" + entityType.getSimpleName().toLowerCase() + "' in database is empty");
        }
    }
}