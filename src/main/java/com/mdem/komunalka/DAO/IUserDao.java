package com.mdem.komunalka.DAO;

import com.mdem.komunalka.model.User;

public interface IUserDao {
    User getUserByUsername(String login);
}
