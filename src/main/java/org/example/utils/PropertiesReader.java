package org.example.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class PropertiesReader implements Reader {
    private final static Logger LOG = LoggerFactory.getLogger(PropertiesReader.class);

    private final Properties properties = new Properties();

    @Override
    public void init(String pathFile) {
        try (FileReader reader = new FileReader(pathFile)) {
            properties.load(reader);
        } catch (IOException e) {
            LOG.error("I/O exception in method init()", e);
        }
    }

    @Override
    public String getProperties(String key) {
        return properties.getProperty(key);
    }
}
