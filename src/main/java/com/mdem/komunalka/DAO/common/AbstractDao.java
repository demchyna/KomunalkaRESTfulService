package com.mdem.komunalka.DAO.common;

import com.mdem.komunalka.DAO.IGenericDao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.GenericTypeResolver;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

@Repository
public class AbstractDao <T extends Serializable> implements IGenericDao<T> {

    private Class<T> entityType = (Class<T>) GenericTypeResolver.resolveTypeArgument(getClass(), AbstractDao.class);

    private static SessionFactory sessionFactory;

    @Autowired
    private void setSessionFactory(SessionFactory sessionFactory) {
        AbstractDao.sessionFactory = sessionFactory;
    }

    protected static Session getSession() {
        return AbstractDao.sessionFactory.getCurrentSession();
    }

    @Override
    public void create(T entity) {
        getSession().save(entity);
    }

    @Override
    public T getById(Long id) {
        T entity = (T) getSession().get(entityType, id);
        return entity;
    }

    @Override
    public void update(T entity) {
        getSession().update(entity);
    }

    @Override
    public void delete(T entity) {
        getSession().delete(entity);
    }

    @Override
    public List<T> getAll() {
        @SuppressWarnings("unchecked")
        List<T> entities = getSession().createQuery("FROM " + entityType.getName()).list();
        if (!entities.isEmpty()) {
            return entities;
        } else {
            return null;
        }
    }
}
