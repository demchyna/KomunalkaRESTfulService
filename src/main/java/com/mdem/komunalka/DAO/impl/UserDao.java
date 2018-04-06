package com.mdem.komunalka.DAO.impl;

import com.mdem.komunalka.DAO.IUserDao;
import com.mdem.komunalka.DAO.common.AbstractDao;
import com.mdem.komunalka.exception.ConflictDataException;
import com.mdem.komunalka.model.User;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;

@Repository
public class UserDao extends AbstractDao<User, Long> implements IUserDao {

    @Override
    public User getUserByUsername(String username) throws NoResultException {
        Query query = getSession().createQuery("FROM " + User.class.getName() + " where username = :username");
        query.setParameter("username", username);
        return (User) query.uniqueResult();
    }

    @Override
    public void create(User entity) {
        User user = getUserByUsername(entity.getUsername());
        if (user == null) {
            super.create(entity);
        } else {
            throw new ConflictDataException("User with username " + entity.getUsername() + " already exists in database");
        }
    }
}
