package com.mdem.komunalka.DAO.common;

import com.mdem.komunalka.DAO.IAbstractDao;
import com.mdem.komunalka.model.common.IEntity;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.sql.SQLException;
import java.util.List;

@Repository
public abstract class AbstractDao<T extends IEntity, K extends Serializable> implements IAbstractDao<T, K> {

    private static SessionFactory sessionFactory;
    private Class<T> entityType;

    public AbstractDao() {
        ParameterizedType genericSuperclass = (ParameterizedType) getClass().getGenericSuperclass();
        this.entityType = (Class<T>) genericSuperclass.getActualTypeArguments()[0];
    }

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
    public T getById(K id) {
        T entity = getSession().get(entityType, id);
        return entity;
    }

    @Override
    public void update(T entity) {
        getSession().merge(entity);
    }

    @Override
    public void delete(T entity) {
        getSession().delete(entity);
    }

    @Override
    public List<T> getAll() {
        @SuppressWarnings("unchecked")
        List<T> entities = getSession().createQuery("FROM " + entityType.getName()).list();
        return entities;
    }
}
