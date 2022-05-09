package org.example.utils;

/**
 * Интерфейс считывания данных для бота!
 */
public interface Reader {

    void init(String pathFile);

    String getProperties(String key);
}
