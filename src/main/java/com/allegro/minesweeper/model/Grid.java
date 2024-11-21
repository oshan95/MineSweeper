package com.allegro.minesweeper.model;

import com.allegro.minesweeper.helper.CellHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Grid {

    private static final Logger LOGGER = LogManager.getLogger(Grid.class);

    private final Cell[][] cellGrid;
    private boolean gameOver;

    public Grid(int size,  int minesCount) {
        cellGrid = new Cell[size][size];
        gameOver = false;
        initializeGridWithCells();
        randomlyPlaceMines(minesCount);
    }

    public Cell[][] getCellGrid() {
        return this.cellGrid;
    }

    private void initializeGridWithCells() {
        for (int i = 0; i < cellGrid.length; i++) {
            for (int j = 0; j < cellGrid[i].length; j++) {
                Cell cell = new Cell(false);
                cell.setCellIndex(new CellIndex(i, j));
                cellGrid[i][j] = cell;
            }
        }
    }

    private void randomlyPlaceMines(int mines) {
        Set<CellIndex> cellIndices = new HashSet<>();

        Random random = new Random();

        while (cellIndices.size() < mines) {
            int randomRow = random.nextInt(cellGrid.length);
            int randomCol = random.nextInt(cellGrid.length);
            cellIndices.add(new CellIndex(randomRow, randomCol));
        }

        for (CellIndex index: cellIndices) {
            cellGrid[index.getRowIndex()][index.getColIndex()].setMine(true);
        }

        logGridCellWithMines();
    }

    public int getCellAdjacentMines(CellIndex cellIndex) {
        return CellHelper.identifyNoOfAdjacentMines(cellGrid, cellGrid[cellIndex.getRowIndex()][cellIndex.getColIndex()]);
    }

    private void logGridCellWithMines() {
        StringBuilder sb = new StringBuilder("");
        for (int col=0; col<cellGrid.length; col++) {
            if (col == 0) {
                sb.append("  " + (col+1) + " ");
            } else {
                sb.append((col+1) + " ");
            }
        }
        sb.append("\n");

        for (int row=0; row<cellGrid.length; row++) {

            char letter = (char)('A' + row);
            sb.append(letter + " ");


            for (int col=0; col<cellGrid.length; col++) {
                Cell currentCell = cellGrid[row][col];
                String cellContent = currentCell.isMine() ? "M" : "_";
                sb.append(cellContent + " ");

            }
            sb.append("\n");
        }
        LOGGER.info("Initialized cell grid with randomly placed mines: \n{}", sb);
    }

    public void print() {
        StringBuilder sb = new StringBuilder("");
        for (int col=0; col<cellGrid.length; col++) {
            if (col == 0) {
                System.out.print("  " + (col+1) + " ");
                sb.append("  " + (col+1) + " ");
            } else {
                System.out.print((col+1) + " ");
                sb.append((col+1) + " ");
            }
        }
        System.out.println();
        sb.append("\n");

        for (int row=0; row<cellGrid.length; row++) {

            char letter = (char)('A' + row);
            System.out.print(letter + " ");
            sb.append(letter + " ");

            for (int col=0; col<cellGrid.length; col++) {
                Cell currentCell = cellGrid[row][col];
                String cellContent = currentCell.isUncovered() ?
                        String.valueOf(CellHelper.identifyNoOfAdjacentMines(cellGrid, currentCell)) : "_";
                System.out.print(cellContent + " ");
                sb.append(cellContent + " ");

            }
            System.out.println();
            sb.append("\n");
        }
        LOGGER.info("Current grid cell: \n{}", sb);
    }

    public void uncoverCell(CellIndex cellIndex) {
        Cell cell = cellGrid[cellIndex.getRowIndex()][cellIndex.getColIndex()];
        if (cell.isMine()) {
            gameOver = true;
        } else {
            uncoverRecursive(cellIndex);
        }
    }

    private void uncoverRecursive(CellIndex cellIndex) {
        Cell cell = cellGrid[cellIndex.getRowIndex()][cellIndex.getColIndex()];
        if (cell.isUncovered()) {
            return;
        }

        cell.setUncovered(true);
        if (CellHelper.identifyNoOfAdjacentMines(cellGrid, cell) == 0) {
            for (Cell adjacentCell : CellHelper.identifyNeighbors(cellGrid, cell)) {
                uncoverRecursive(adjacentCell.getCellIndex());
            }
        }
    }

    public boolean isGameOver() {
        return this.gameOver;
    }

}
