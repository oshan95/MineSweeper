package com.allegro.minesweeper.helper;

import com.allegro.minesweeper.model.Cell;
import com.allegro.minesweeper.model.CellIndex;
import com.allegro.minesweeper.model.CellNeighborDirection;

import java.util.ArrayList;
import java.util.List;

public class CellHelper {

    //private default constructor to avoid instantiation of helper
    private CellHelper() {
    }

    public static CellIndex identifyCell(String userInput, int gridSize) {
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

        if (!isValidCellLocation(rowIndex, colIndex, gridSize)) {
            throw new IllegalArgumentException("User input is out of bounds.");
        }

        return new CellIndex(rowIndex, colIndex);
    }

    public static List<Cell> identifyNeighbors(Cell[][] grid, Cell cell) {
        List<Cell> neighbors = new ArrayList<>();

        int row = cell.getCellIndex().getRowIndex();
        int col = cell.getCellIndex().getColIndex();

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

    public static int identifyNoOfAdjacentMines(Cell[][] grid, Cell cell) {
        int count = 0;

        int row = cell.getCellIndex().getRowIndex();
        int col = cell.getCellIndex().getColIndex();

        for (CellNeighborDirection direction : CellNeighborDirection.values()) {
            int neighborRow = row + direction.getRowOffset();
            int neighborCol = col + direction.getColOffset();

            // Check if the neighbor is within bounds
            if (neighborRow >= 0 && neighborRow < grid.length && neighborCol >= 0 && neighborCol < grid[neighborRow].length) {
                Cell neighbor = grid[neighborRow][neighborCol];
                count += neighbor.isMine() ? 1 : 0;
            }
        }

        return count;
    }

    private static boolean isValidCellInput(char row, char column) {
        boolean withinAlphabet = (row >= 'A' && row <= 'Z');
        return withinAlphabet && Character.isDigit(column);
    }

    private static boolean isValidCellLocation(int rowIndex, int columnIndex, int gridSize) {
        boolean rowIndexWithinBounds = (rowIndex >= 0 && rowIndex < gridSize);
//        boolean columnIndexWithinBounds = (columnIndex >= 0 && columnIndex < grid[0].length);
        boolean columnIndexWithinBounds = (columnIndex >= 0 && columnIndex < gridSize);
        return rowIndexWithinBounds && columnIndexWithinBounds;
    }

}
