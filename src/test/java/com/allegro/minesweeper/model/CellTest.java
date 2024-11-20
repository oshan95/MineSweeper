package com.allegro.minesweeper.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class CellTest {

    @Test
    void testConstructMineCell() {
        Cell cell = new Cell(true);
        assertTrue(cell.isMine());
    }

    @Test
    void testConstructNonMineCell() {
        Cell cell = new Cell(false);
        assertFalse(cell.isMine());
    }

    @Test
    void testConstructCellAndSetMine() {
        Cell cell = new Cell(false);
        cell.setMine(true);
        assertTrue(cell.isMine());
    }

    @Test
    void testConstructCellAndSetAdjacentMines() {
        Cell cell = new Cell(false);
        cell.setAdjacentMines(3);
        assertEquals(3, cell.getAdjacentMines());
    }

    @Test
    void testConstructCellAndSetUncovered() {
        Cell cell = new Cell(false);
        cell.setUncovered(true);
        assertTrue(cell.isUncovered());
    }

    @Test
    void testConstructCellAndSetRowIndex() {
        Cell cell = new Cell(false);
        cell.setCellRowIndex(-1);
        assertEquals(-1, cell.getCellRowIndex());
    }

    @Test
    void testConstructCellAndSetColumnIndex() {
        Cell cell = new Cell(false);
        cell.setCellColumnIndex(1);
        assertEquals(1, cell.getCellColumnIndex());
    }
}
