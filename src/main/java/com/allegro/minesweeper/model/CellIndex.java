package com.allegro.minesweeper.model;

import java.util.Objects;

public class CellIndex {

    private int rowIndex;
    private int colIndex;

    public CellIndex(int rowIndex, int colIndex) {
        this.rowIndex = rowIndex;
        this.colIndex = colIndex;
    }

    public CellIndex getIndex() {
        return this;
    }

    public void setIndex(int rowIndex, int colIndex) {
        this.rowIndex = rowIndex;
        this.colIndex = colIndex;
    }

    public int getRowIndex() {
        return this.rowIndex;
    }

    public int getColIndex() {
        return this.colIndex;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        } else {
            CellIndex cellIndex = (CellIndex) obj;
            return this.rowIndex == cellIndex.getRowIndex() && this.colIndex == cellIndex.getColIndex();
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(rowIndex, colIndex);
    }

}
