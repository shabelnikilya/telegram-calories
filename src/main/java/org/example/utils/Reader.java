package org.example.utils;

/**
 * Интерфейс считывания данных для бота!
 */
@Deprecated
public interface Reader {

    void init(String pathFile);

    String getProperties(String key);
}
