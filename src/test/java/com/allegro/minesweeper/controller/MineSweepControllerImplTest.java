package com.allegro.minesweeper.controller;

import com.allegro.minesweeper.config.MessageLoader;
import com.allegro.minesweeper.controller.impl.MineSweepControllerImpl;
import com.allegro.minesweeper.model.Grid;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.InputMismatchException;
import java.util.Scanner;

import static org.mockito.Mockito.*;

public class MineSweepControllerImplTest {


    private MineSweepControllerImpl controller;

    @Mock
    private MessageLoader messageLoader;

    @Mock
    private Scanner scanner;

    @Mock
    private Grid grid;

    private final int retryAttempts = 3;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        controller = new MineSweepControllerImpl(messageLoader, scanner, retryAttempts);
    }

    @Test
    public void testStartGame_validInput() {
        when(messageLoader.getMessage("minesweeper.prompt.welcome")).thenReturn("Welcome to Minesweeper!");
        when(messageLoader.getMessage("minesweeper.prompt.gridSize")).thenReturn("Enter grid size:");
        when(scanner.nextInt()).thenReturn(5).thenReturn(3); // Grid size = 5, Mines = 3
        when(messageLoader.getMessage("minesweeper.prompt.mineField")).thenReturn("Minefield:");
        when(messageLoader.getMessage("minesweeper.prompt.selectSquare")).thenReturn("Select a square:");

        doNothing().when(grid).print();

        controller.startGame();

        verify(messageLoader, times(1)).getMessage("minesweeper.prompt.welcome");
        verify(messageLoader, times(1)).getMessage("minesweeper.prompt.gridSize");
        verify(scanner, times(2)).nextInt(); // Grid size and mine count inputs
    }

    @Test
    public void testStartGame_invalidGridSizeExceedsRetry() {
        when(messageLoader.getMessage("minesweeper.prompt.welcome")).thenReturn("Welcome to Minesweeper!");
        when(messageLoader.getMessage("minesweeper.prompt.gridSize")).thenReturn("Enter grid size:");
        when(messageLoader.getMessage("minesweeper.prompt.invalidInput")).thenReturn("Invalid input.");
        when(messageLoader.getMessage("minesweeper.prompt.maxRetryReached")).thenReturn("Max retry attempts reached.");
        when(scanner.nextInt()).thenThrow(InputMismatchException.class);

        controller.startGame();

        verify(messageLoader, times(retryAttempts)).getMessage("minesweeper.prompt.invalidInput");
        verify(messageLoader, times(1)).getMessage("minesweeper.prompt.maxRetryReached");
    }

}
