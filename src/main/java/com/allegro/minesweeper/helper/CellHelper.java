package com.allegro.minesweeper.helper;

import com.allegro.minesweeper.model.Cell;
import com.allegro.minesweeper.model.CellNeighborDirection;

import java.util.ArrayList;
import java.util.List;

public class CellHelper {

    //private default constructor to avoid instantiation of helper
    private CellHelper() {
    }

    public static Cell identifyCell(String userInput, Cell[][] grid) {
        if (userInput == null || userInput.length() != 2) {
            throw new IllegalStateException("User have entered an invalid value.");
        }

        char row = Character.toUpperCase(userInput.charAt(0));
        char column = userInput.charAt(1);

        if (!isValidCellInput(row, column)) {
            throw new IllegalStateException("User have entered an invalid value.");
        }

        int rowIndex = row - 'A';
        int colIndex = Integer.parseInt(String.valueOf(column)) - 1;

        if (!isValidCellLocation(rowIndex, colIndex, grid)) {
            throw new IllegalArgumentException("User input is out of bounds.");
        }

        return grid[rowIndex][colIndex];
    }

    public static List<Cell> identifyNeighbors(Cell[][] grid, Cell cell) {
        List<Cell> neighbors = new ArrayList<>();

        int row = cell.getCellRowIndex();
        int col = cell.getCellColumnIndex();

        for (CellNeighborDirection direction : CellNeighborDirection.values()) {
            int neighborRow = row + direction.getRowOffset();
            int neighborCol = col + direction.getColOffset();

            // Check if the neighbor is within bounds
            if (neighborRow >= 0 && neighborRow < grid.length && neighborCol >= 0 && neighborCol < grid[neighborRow].length) {
                neighbors.add(grid[neighborRow][neighborCol]);
            }
        }

        return neighbors;
    }

    private static boolean isValidCellInput(char row, char column) {
        boolean withinAlphabet = (row >= 'A' && row <= 'Z');
        return withinAlphabet && Character.isDigit(column);
    }

    private static boolean isValidCellLocation(int rowIndex, int columnIndex, Cell[][] grid) {
        boolean rowIndexWithinBounds = (rowIndex >= 0 && rowIndex < grid.length);
        boolean columnIndexWithinBounds = (columnIndex >= 0 && columnIndex < grid[0].length);
        return rowIndexWithinBounds && columnIndexWithinBounds;
    }

}
