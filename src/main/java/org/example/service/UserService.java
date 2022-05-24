package org.example.service;

import org.example.models.User;

public interface UserService {

    User findByUserName(String userName);

    void save(User user);

    void update(User user);

    void remove(User user);
}
