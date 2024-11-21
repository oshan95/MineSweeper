package com.allegro.minesweeper.helper;

import com.allegro.minesweeper.model.Cell;
import com.allegro.minesweeper.model.CellIndex;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CellHelperTest {

    @Test
    public void testIdentifyCell_ValidInput() {
        CellIndex cellIndex = CellHelper.identifyCell("A1", 5);
        assertEquals(0, cellIndex.getRowIndex(), "Row index should be 0 for A1");
        assertEquals(0, cellIndex.getColIndex(), "Column index should be 0 for A1");
    }

    @Test
    public void testIdentifyCell_InvalidInput_LengthTooLong() {
        assertThrows(IllegalStateException.class, () -> {
            CellHelper.identifyCell("A10", 5);
        }, "User have entered an invalid value.");
    }

    @Test
    public void testIdentifyCell_InvalidInput_NonAlphaRow() {
        assertThrows(IllegalStateException.class, () -> {
            CellHelper.identifyCell("1A", 5);
        }, "User have entered an invalid value.");
    }

    @Test
    public void testIdentifyCell_OutOfBounds() {
        assertThrows(IllegalArgumentException.class, () -> {
            CellHelper.identifyCell("F1", 5); // Grid size is 5, so E1 should be out of bounds
        }, "User input is out of bounds.");
    }

    @Test
    public void testIdentifyNeighbors_CornerCell() {
        // Create a 3x3 grid with cells at (0,0) to (2,2)
        Cell[][] grid = new Cell[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                grid[i][j] = mock(Cell.class);
                when(grid[i][j].getCellIndex()).thenReturn(new CellIndex(i, j));
            }
        }
        Cell cornerCell = grid[0][0];
        List<Cell> neighbors = CellHelper.identifyNeighbors(grid, cornerCell);

        assertEquals(3, neighbors.size(), "Top-left corner should have 3 neighbors");
    }

    @Test
    public void testIdentifyNeighbors_MiddleCell() {
        // Create a 3x3 grid with cells at (0,0) to (2,2)
        Cell[][] grid = new Cell[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                grid[i][j] = mock(Cell.class);
                when(grid[i][j].getCellIndex()).thenReturn(new CellIndex(i, j));
            }
        }
        Cell middleCell = grid[1][1];
        List<Cell> neighbors = CellHelper.identifyNeighbors(grid, middleCell);

        assertEquals(8, neighbors.size(), "Middle cell should have 8 neighbors");
    }

    @Test
    public void testIdentifyNoOfAdjacentMines_ZeroMines() {
        // Create a 3x3 grid where no cells are mines
        Cell[][] grid = new Cell[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                grid[i][j] = mock(Cell.class);
                when(grid[i][j].isMine()).thenReturn(false);
                when(grid[i][j].getCellIndex()).thenReturn(new CellIndex(0,1));
            }
        }

        // Test a cell with no adjacent mines
        CellIndex testIndex = new CellIndex(1, 1);
        int mineCount = CellHelper.identifyNoOfAdjacentMines(grid, grid[testIndex.getRowIndex()][testIndex.getColIndex()]);
        assertEquals(0, mineCount, "Cell with no adjacent mines should return 0");
    }

    @Test
    public void testIdentifyNoOfAdjacentMines_SomeMines() {
        // Create a 3x3 grid where some cells are mines
        Cell[][] grid = new Cell[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                grid[i][j] = mock(Cell.class);
                when(grid[i][j].isMine()).thenReturn(false);
                when(grid[i][j].getCellIndex()).thenReturn(new CellIndex(i,j));
            }
        }

        // Set mines at (0, 0) and (0, 1)
        when(grid[0][0].isMine()).thenReturn(true);
        when(grid[0][1].isMine()).thenReturn(true);

        // Test a cell with adjacent mines
        CellIndex testIndex = new CellIndex(1, 1);
        int mineCount = CellHelper.identifyNoOfAdjacentMines(grid, grid[testIndex.getRowIndex()][testIndex.getColIndex()]);
        assertEquals(2, mineCount, "Cell with adjacent mines should return the correct count");
    }
}
