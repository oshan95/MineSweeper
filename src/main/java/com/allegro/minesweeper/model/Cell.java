package com.allegro.minesweeper.model;

public class Cell {
    private boolean isMine;
    private boolean isUncovered;
    private int adjacentMines;

    public Cell(boolean isMine) {
        this.isMine = isMine;
        this.isUncovered = false;
        this.adjacentMines = 0;
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
}
