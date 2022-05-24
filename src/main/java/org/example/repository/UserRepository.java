package org.example.repository;

import org.example.models.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository {

    User findByUserName(String userName);

    void save(User user);

    void update(User user);

    void delete(User user);
}
