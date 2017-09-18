package com.mdem.komunalka.DAO.impl;

import com.mdem.komunalka.DAO.IUserDao;
import com.mdem.komunalka.DAO.common.AbstractDao;
import com.mdem.komunalka.model.User;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class UserDao extends AbstractDao<User, Long> implements IUserDao {

    @Override
    public User getUserByUsername(String username) {
        Query query = getSession().createQuery("FROM " + User.class.getName() + " where username = :username");
        query.setParameter("username", username);
        return (User) query.getSingleResult();
    }
}
