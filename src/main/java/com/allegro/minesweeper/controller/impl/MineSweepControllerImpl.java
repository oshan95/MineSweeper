package com.allegro.minesweeper.controller.impl;

import com.allegro.minesweeper.config.MessageLoader;
import com.allegro.minesweeper.controller.MineSweepController;
import com.allegro.minesweeper.helper.CellHelper;
import com.allegro.minesweeper.model.CellIndex;
import com.allegro.minesweeper.model.Grid;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MineSweepControllerImpl implements MineSweepController {

    private static final Logger LOGGER = LogManager.getLogger(MineSweepControllerImpl.class);

    private final static String WELCOME_MESSAGE_PROP_KEY = "minesweeper.prompt.welcome";
    private final static String GRID_SIZE_MESSAGE_PROP_KEY = "minesweeper.prompt.gridSize";
    private final static String MINES_MESSAGE_PROP_KEY = "minesweeper.prompt.mineCount";
    private final static String SQUARE_REVEAL_MESSAGE_PROP_KEY = "minesweeper.prompt.selectSquare";
    private final static String ADJACENT_MINES_MESSAGE_PROP_KEY = "minesweeper.prompt.adjacentMines";
    private final static String CONGRATS_MESSAGE_PROP_KEY = "minesweeper.prompt.state.gameWon";
    private final static String LOST_MESSAGE_PROP_KEY = "minesweeper.prompt.state.gameLose";
    private final static String RESTART_MESSAGE_PROP_KEY = "minesweeper.prompt.state.restart";
    private final static String INVALID_INPUT_MESSAGE_PROP_KEY = "minesweeper.prompt.invalidInput";
    private final static String MAX_RETRY_REACHED_MESSAGE_PROP_KEY = "minesweeper.prompt.maxRetryReached";
    private final static String MINE_FIELD_MESSAGE_PROP_KEY = "minesweeper.prompt.mineField";
    private final static String UPDATED_MINE_FIELD_MESSAGE_PROP_KEY ="minesweeper.prompt.updatedMineField";
    private final MessageLoader messageLoader;
    private final Scanner scanner;
    private final int retryAttempts;

    public MineSweepControllerImpl(MessageLoader messageLoader, Scanner scanner, int retryAttempts) {
        this.messageLoader = messageLoader;
        this.scanner = scanner;
        this.retryAttempts = retryAttempts;
    }

    @Override
    public void startGame() {

        LOGGER.info("User started the game");

        System.out.println(messageLoader.getMessage(WELCOME_MESSAGE_PROP_KEY));
        System.out.println();
        System.out.println(messageLoader.getMessage(GRID_SIZE_MESSAGE_PROP_KEY));
        int gridSize = getIntInput();

        if (gridSize == 0) {
            LOGGER.info("User didn't provide valid input for grid size and exceeded maximum retry attempts. Terminating game.");
            return;
        }
        LOGGER.info("User provided grid size: {}", gridSize);

        System.out.println(messageLoader.getMessage(MINES_MESSAGE_PROP_KEY));
        int mines = getIntInput();

        if (mines == 0) {
            LOGGER.info("User didn't provide valid input for number of mines and exceeded maximum retry attempts. Terminating game.");
            return;
        }
        LOGGER.info("User provided no of mines: {}", mines);

        System.out.println();

        Grid grid = new Grid(gridSize, mines);

        System.out.println(messageLoader.getMessage(MINE_FIELD_MESSAGE_PROP_KEY));
        grid.print();
        System.out.println();

        revealSquareSteps(grid, gridSize);

    }

    private void revealSquareSteps(Grid grid, int gridSize) {

        boolean isFirst = true;

        while (!grid.isGameOver()) {

            System.out.print(messageLoader.getMessage(SQUARE_REVEAL_MESSAGE_PROP_KEY));

            int count = 0;

            boolean issue = true;
            while (count < retryAttempts && issue) {
                try {
                    count++;
                    String cellInput = scanner.nextLine();
                    LOGGER.info("User entered {} to reveal", cellInput);

                    CellIndex cellIndex = CellHelper.identifyCell(cellInput, gridSize);
                    int mines = grid.getCellAdjacentMines(cellIndex);
                    grid.uncoverCell(cellIndex);
                    if (!grid.isGameOver()) {
                        System.out.println(messageLoader.getFormattedMessage(ADJACENT_MINES_MESSAGE_PROP_KEY, mines));
                        System.out.println();
                        System.out.println(messageLoader.getMessage(UPDATED_MINE_FIELD_MESSAGE_PROP_KEY));
                        grid.print();
                        System.out.println();
                    }
                    issue = false;
                } catch (IllegalStateException | IllegalArgumentException ex) {
                    if (count == retryAttempts) {
                        LOGGER.error("User entered cell location is invalid and reached max attempts to re-enter. Error: {}", ex.getMessage());
                        System.out.println(messageLoader.getMessage(MAX_RETRY_REACHED_MESSAGE_PROP_KEY));
                        return;
                    } else {
                        LOGGER.error("User entered cell location is invalid. Error: {}", ex.getMessage());
                        System.out.println(messageLoader.getMessage(INVALID_INPUT_MESSAGE_PROP_KEY));
                    }
                }
            }

        }
        System.out.println(messageLoader.getMessage(LOST_MESSAGE_PROP_KEY));
        LOGGER.info("User selected mine field to reveal. Game over!");
        return;

    }

    private int getIntInput() {
        int input = 0;
        int count = 0;
        boolean isFirst = true;
        while (count < retryAttempts) {
            try {
                input = scanner.nextInt();
                scanner.nextLine();
                return input;
            } catch (InputMismatchException ex) {

                if (isFirst) {
                    isFirst = false;
                } else {
                    count++;
                }

                scanner.nextLine();
                if (count == retryAttempts) {
                    System.out.println(messageLoader.getMessage(MAX_RETRY_REACHED_MESSAGE_PROP_KEY));
                    LOGGER.error("User entered invalid input and reached max retries. Error: {}", ex.getMessage());
                } else {
                    System.out.println(messageLoader.getMessage(INVALID_INPUT_MESSAGE_PROP_KEY));
                    LOGGER.error("User entered invalid input. Error: {}", ex.getMessage());
                }
            }
        }
        return input;
    }

}
