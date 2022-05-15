package org.example.service;

import java.util.List;

public interface Service<T> {

    T findById(int id);

    T findByName(String name);

    List<T> findAll();

    void save(T t);

    void update(T t);

    void remove(T t);
}
