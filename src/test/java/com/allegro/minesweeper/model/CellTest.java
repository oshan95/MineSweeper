package com.allegro.minesweeper.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class CellTest {

    @Test
    public void testConstructMineCell() {
        Cell cell = new Cell(true);
        assertTrue(cell.isMine());
    }

    @Test
    public void testConstructNonMineCell() {
        Cell cell = new Cell(false);
        assertFalse(cell.isMine());
    }

    @Test
    public void testConstructCellAndSetMine() {
        Cell cell = new Cell(false);
        cell.setMine(true);
        assertTrue(cell.isMine());
    }

    @Test
    public void testConstructCellAndSetAdjacentMines() {
        Cell cell = new Cell(false);
        cell.setAdjacentMines(3);
        assertEquals(3, cell.getAdjacentMines());
    }

    @Test
    public void testConstructCellAndSetUncovered() {
        Cell cell = new Cell(false);
        cell.setUncovered(true);
        assertTrue(cell.isUncovered());
    }

    @Test
    public void testConstructCellAndSetIndex() {
        Cell cell = new Cell(false);
        cell.setCellIndex(new CellIndex(1, 0));
        assertEquals(1, cell.getCellIndex().getRowIndex());
        assertEquals(0, cell.getCellIndex().getColIndex());
    }
}
