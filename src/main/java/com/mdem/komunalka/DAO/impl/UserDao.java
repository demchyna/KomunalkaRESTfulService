package com.mdem.komunalka.DAO.impl;

import com.mdem.komunalka.DAO.common.AbstractDao;
import com.mdem.komunalka.model.User;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class UserDao extends AbstractDao<User, Long> {

    public User getUserByLogin(String login) {

        Query query= getSession().createQuery("FROM " + User.class.getName() + " WHERE login =: login");
        query.setParameter("login", login);
        User user =  (User) query.uniqueResult();

        return user;
    }
}