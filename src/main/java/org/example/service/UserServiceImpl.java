package org.example.service;

import org.example.models.UserDB;
import org.example.repository.UserRepository;
import org.example.repository.UserRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository repository;

    @Autowired
    public UserServiceImpl(UserRepositoryImpl repository) {
        this.repository = repository;
    }

    @Override
    public UserDB findByUserName(String userName) {
        return repository.findByUserName(userName);
    }

    @Override
    public void save(UserDB user) {
        repository.save(user);
    }

    @Override
    public void update(UserDB user) {
        repository.update(user);
    }

    @Override
    public void remove(UserDB user) {
        repository.delete(user);
    }
}
