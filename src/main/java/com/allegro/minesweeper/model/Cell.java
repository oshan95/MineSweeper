package com.allegro.minesweeper.model;

public class Cell {
    private boolean isMine;
    private boolean isUncovered;
    private int adjacentMines;
    private CellIndex cellIndex;

    public Cell(boolean isMine) {
        this.isMine = isMine;
        this.isUncovered = false;
        this.adjacentMines = 0;
        this.cellIndex = new CellIndex(0,0);
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

    public CellIndex getCellIndex() {
        return this.cellIndex;
    }

    public void setCellIndex(CellIndex cellIndex) {
        this.cellIndex = cellIndex;
    }
}
