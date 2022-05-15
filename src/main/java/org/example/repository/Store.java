package org.example.repository;

import java.util.List;

public interface Store<T> {

    T findById(int id);

    T findByName(String name);

    List<T> findAll();

    void save(T t);

    void update(T t);

    void remove(T t);
}
