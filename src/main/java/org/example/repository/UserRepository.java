package org.example.repository;

import org.example.models.UserDB;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository {

    UserDB findByUserName(String userName);

    void save(UserDB user);

    void update(UserDB user);

    void delete(UserDB user);
}
