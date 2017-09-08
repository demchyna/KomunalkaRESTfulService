package com.mdem.komunalka.service.impl;


import com.mdem.komunalka.DAO.impl.UserDao;
import com.mdem.komunalka.exception.DataNotFoundException;
import com.mdem.komunalka.model.User;
import com.mdem.komunalka.service.IUserService;
import com.mdem.komunalka.service.common.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class UserService extends AbstractService<User, Long> implements IUserService {

    @Autowired
    private UserDao userDao;

    @Override
    @Transactional
    public User getUserByLogin(String login) {

        User user = userDao.getUserByLogin(login);
        if (user != null) {
            return user;
        } else {
            throw new DataNotFoundException("User with login " + login  +" not found in database");
        }
    }
}