package com.allegro.minesweeper.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class CellNeighborDirectionTest {

    private static final int ENUM_LENGTH = 8;

    @Test
    void testEnumLength() {
        assertEquals(ENUM_LENGTH, CellNeighborDirection.values().length);
    }

    @Test
    void testEnumValues() {
        assertNotNull(CellNeighborDirection.valueOf("TOP_LEFT"));
        assertNotNull(CellNeighborDirection.valueOf("TOP_CENTER"));
        assertNotNull(CellNeighborDirection.valueOf("TOP_RIGHT"));
        assertNotNull(CellNeighborDirection.valueOf("CENTER_LEFT"));
        assertNotNull(CellNeighborDirection.valueOf("CENTER_RIGHT"));
        assertNotNull(CellNeighborDirection.valueOf("BOTTOM_LEFT"));
        assertNotNull(CellNeighborDirection.valueOf("BOTTOM_CENTER"));
        assertNotNull(CellNeighborDirection.valueOf("BOTTOM_RIGHT"));
    }

    @Test
    void testTopRowOffsets() {
        assertEquals(-1, CellNeighborDirection.TOP_LEFT.getRowOffset());
        assertEquals(-1, CellNeighborDirection.TOP_LEFT.getColOffset());

        assertEquals(-1, CellNeighborDirection.TOP_CENTER.getRowOffset());
        assertEquals(0, CellNeighborDirection.TOP_CENTER.getColOffset());

        assertEquals(-1, CellNeighborDirection.TOP_RIGHT.getRowOffset());
        assertEquals(1, CellNeighborDirection.TOP_RIGHT.getColOffset());
    }

    @Test
    void testCenterRowOffsets() {
        assertEquals(0, CellNeighborDirection.CENTER_LEFT.getRowOffset());
        assertEquals(-1, CellNeighborDirection.CENTER_LEFT.getColOffset());

        assertEquals(0, CellNeighborDirection.CENTER_RIGHT.getRowOffset());
        assertEquals(1, CellNeighborDirection.CENTER_RIGHT.getColOffset());
    }

    @Test
    void testBottomRowOffsets() {
        assertEquals(1, CellNeighborDirection.BOTTOM_LEFT.getRowOffset());
        assertEquals(-1, CellNeighborDirection.BOTTOM_LEFT.getColOffset());

        assertEquals(1, CellNeighborDirection.BOTTOM_CENTER.getRowOffset());
        assertEquals(0, CellNeighborDirection.BOTTOM_CENTER.getColOffset());

        assertEquals(1, CellNeighborDirection.BOTTOM_RIGHT.getRowOffset());
        assertEquals(1, CellNeighborDirection.BOTTOM_RIGHT.getColOffset());
    }

}
