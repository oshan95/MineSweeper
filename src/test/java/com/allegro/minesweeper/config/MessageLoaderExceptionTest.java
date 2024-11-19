package com.allegro.minesweeper.config;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class MessageLoaderExceptionTest {
    private final static String INVALID_MESSAGE_PROPERTIES_FILE = "msg.properties";

    @Test
    void testMissingLoadMessagePropertiesFile() {
        assertThrows(IllegalArgumentException.class, ()-> {
            MessageLoader.getInstance(INVALID_MESSAGE_PROPERTIES_FILE);
        });
    }
}
