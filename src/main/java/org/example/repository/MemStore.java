package org.example.repository;

public interface MemStore<T> {

    void init();

    T findByMessage(String message);

    void save(String message, T t);

    void update(String message, T t);

    void delete(String message);
}
