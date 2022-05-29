package org.example.service;

import org.example.models.UserDB;

public interface UserService {

    UserDB findByUserName(String userName);

    void save(UserDB user);

    void update(UserDB user);

    void remove(UserDB user);
}
