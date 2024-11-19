package com.allegro.minesweeper.config;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MessageLoaderTest {

    private final static String MESSAGE_PROPERTIES_FILE = "messages.properties";
    private final static String MESSAGE_GAME_WON = "Congratulations, you have won the game!";
    private final static String MESSAGE_ADJACENT_MINES = "This square contains 2 adjacent mines.";
    private final static String MESSAGE_NOT_FOUND = "Message not found: game.minesweeper.prompt.state.gameWon";
    private final static String INVALID_KEY = "game.minesweeper.prompt.state.gameWon";
    private final static String GAME_WON_VALID_KEY = "minesweeper.prompt.state.gameWon";
    private final static String ADJACENT_MINES_VALID_KEY = "minesweeper.prompt.adjacentMines";

    private MessageLoader messageLoader;

    @BeforeEach
    void setup() {
        messageLoader = MessageLoader.getInstance(MESSAGE_PROPERTIES_FILE);
    }

    @Test
    void testLoadMessagePropertiesFile() {
        assertNotNull(messageLoader);
    }

    @Test
    void testGetMessageForValidKey() {
        String message = messageLoader.getMessage(GAME_WON_VALID_KEY);
        assertEquals(MESSAGE_GAME_WON, message);
    }

    @Test
    void testGetFormattedMessageForValidKey() {
        String message = messageLoader.getFormattedMessage(ADJACENT_MINES_VALID_KEY, 2 );
        assertEquals(MESSAGE_ADJACENT_MINES, message);
    }

    @Test
    void testGetMessageForInvalidKey() {
        String message = messageLoader.getMessage(INVALID_KEY);
        assertEquals(MESSAGE_NOT_FOUND, message);
    }
}
