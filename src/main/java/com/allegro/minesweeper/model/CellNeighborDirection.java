package com.allegro.minesweeper.model;

public enum CellNeighborDirection {

    TOP_LEFT(-1, -1),
    TOP_CENTER(-1, 0),
    TOP_RIGHT(-1, 1),
    CENTER_LEFT(0, -1),
    CENTER_RIGHT(0, 1),
    BOTTOM_LEFT(1, -1),
    BOTTOM_CENTER(1, 0),
    BOTTOM_RIGHT(1, 1);

    private final int rowOffset;
    private final int colOffset;

    CellNeighborDirection(int rowOffset, int colOffset) {
        this.rowOffset = rowOffset;
        this.colOffset = colOffset;
    }

    public int getRowOffset() {
        return rowOffset;
    }

    public int getColOffset() {
        return colOffset;
    }
}
