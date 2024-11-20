package com.allegro.minesweeper.model;

public class Cell {
    private boolean isMine;
    private boolean isUncovered;
    private int adjacentMines;
    private int cellRowIndex;
    private int cellColumnIndex;

    public Cell(boolean isMine) {
        this.isMine = isMine;
        this.isUncovered = false;
        this.adjacentMines = 0;
        this.cellRowIndex = 0;
        this.cellColumnIndex = 0;
    }

    public boolean isMine() {
        return isMine;
    }

    public void setMine(boolean mine) {
        isMine = mine;
    }

    public boolean isUncovered() {
        return isUncovered;
    }

    public void setUncovered(boolean uncovered) {
        isUncovered = uncovered;
    }

    public int getAdjacentMines() {
        return adjacentMines;
    }

    public void setAdjacentMines(int adjacentMines) {
        this.adjacentMines = adjacentMines;
    }

    public int getCellRowIndex() {
       return this.cellRowIndex;
    }

    public void setCellRowIndex(int rowIndex) {
        this.cellRowIndex = rowIndex;
    }

    public int getCellColumnIndex() {
        return this.cellColumnIndex;
    }

    public void setCellColumnIndex(int columnIndex) {
        this.cellColumnIndex = columnIndex;
    }
}
