package com.allegro.minesweeper.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class MessageLoader {
    private static MessageLoader instance;
    private final Properties properties;

    private MessageLoader(String propertiesFileName) {
        properties = new Properties();
        try (InputStream input = getClass().getClassLoader().getResourceAsStream(propertiesFileName)) {
            if (input == null) {
                throw new IllegalArgumentException("Properties file not found: " + propertiesFileName);
            }
            properties.load(input);
        } catch (IOException e) {
            throw new IllegalStateException("Error loading properties file: " + propertiesFileName, e);
        }
    }

    public static MessageLoader getInstance(String propertyFile) {
        if (instance == null) {
            instance = new MessageLoader(propertyFile);
        }
        return instance;
    }

    public String getMessage(String key) {
        return properties.getProperty(key, "Message not found: " + key);
    }

    public String getFormattedMessage(String key, Object... args) {
        String template = getMessage(key);
        return String.format(template, args);
    }
}

