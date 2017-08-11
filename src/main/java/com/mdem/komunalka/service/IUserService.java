package com.mdem.komunalka.service;

import com.mdem.komunalka.model.User;

public interface IUserService {
    public User getUserByLogin(String login);
}
