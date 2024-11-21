package com.allegro.minesweeper.model;

import com.allegro.minesweeper.helper.CellHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class GridTest {

    private Grid grid;
    private final int gridSize = 5;
    private final int minesCount = 3;

    @BeforeEach
    public void setUp() {
        grid = new Grid(gridSize, minesCount);
    }

    @Test
    public void testGridInitialization() {
        // Verify the grid is initialized correctly
        assertNotNull(grid, "Grid should not be null");
    }

    @Test
    public void testRandomlyPlaceMines() {
        // Verify the correct number of mines are placed
        int mineCount = 0;
        for (int row = 0; row < gridSize; row++) {
            for (int col = 0; col < gridSize; col++) {
                if (grid.getCellGrid()[row][col].isMine()) {
                    mineCount++;
                }
            }
        }
        assertEquals(minesCount, mineCount, "Number of mines should match the specified count");
    }

    @Test
    public void testGetCellAdjacentMines() {
        try (MockedStatic<CellHelper> mockedCellHelper = mockStatic(CellHelper.class)) {
            CellIndex cellIndex = new CellIndex(2, 2);
            when(CellHelper.identifyNoOfAdjacentMines(any(), any())).thenReturn(2);

            int adjacentMines = grid.getCellAdjacentMines(cellIndex);
            assertEquals(2, adjacentMines, "Should return the correct count of adjacent mines");
            mockedCellHelper.verify(() -> CellHelper.identifyNoOfAdjacentMines(any(), any()), times(1));
        }
    }

    @Test
    public void testUncoverCell_GameOver() {
        // Simulate uncovering a mine
        grid.getCellGrid()[0][0].setMine(true);
        grid.uncoverCell(new CellIndex(0, 0));

        assertTrue(grid.isGameOver(), "Game should be over if a mine is uncovered");
    }

    @Test
    public void testIsGameOver_InitiallyFalse() {
        assertFalse(grid.isGameOver(), "Game should not be over at the start");
    }

    @Test
    public void testIsGameOver_AfterMineUncovered() {
        grid.getCellGrid()[0][0].setMine(true);
        grid.uncoverCell(new CellIndex(0, 0));
        assertTrue(grid.isGameOver(), "Game should be over if a mine is uncovered");
    }
}
