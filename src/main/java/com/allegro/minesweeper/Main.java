package com.allegro.minesweeper;

import com.allegro.minesweeper.config.MessageLoader;
import com.allegro.minesweeper.controller.MineSweepController;
import com.allegro.minesweeper.controller.impl.MineSweepControllerImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Scanner;

public class Main {

    private static final Logger LOGGER = LogManager.getLogger(Main.class);

    private static final MessageLoader messageLoader = MessageLoader.getInstance("messages.properties");
    private static final Scanner scanner = new Scanner(System.in);
    private static final int MAX_RETRY_ATTEMPTS = 3;

    public static void main(String[] args) {

        MineSweepController gameController = new MineSweepControllerImpl(messageLoader, scanner, MAX_RETRY_ATTEMPTS);
        gameController.startGame();

    }
}