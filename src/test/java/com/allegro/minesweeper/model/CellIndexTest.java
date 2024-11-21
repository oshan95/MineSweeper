package com.allegro.minesweeper.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CellIndexTest {
    @Test
    public void testConstructorAndGetters() {
        // Arrange
        int rowIndex = 2;
        int colIndex = 3;

        // Act
        CellIndex cellIndex = new CellIndex(rowIndex, colIndex);

        // Assert
        assertEquals(rowIndex, cellIndex.getRowIndex());
        assertEquals(colIndex, cellIndex.getColIndex());
    }

    @Test
    public void testSetIndex() {
        // Arrange
        CellIndex cellIndex = new CellIndex(0, 0);

        // Act
        cellIndex.setIndex(4, 5);

        // Assert
        assertEquals(4, cellIndex.getRowIndex());
        assertEquals(5, cellIndex.getColIndex());
    }

    @Test
    public void testEquals_SameObject() {
        // Arrange
        CellIndex cellIndex = new CellIndex(1, 1);

        // Act & Assert
        assertTrue(cellIndex.equals(cellIndex)); // Same object comparison
    }

    @Test
    public void testEquals_DifferentObjectsWithSameValues() {
        // Arrange
        CellIndex cellIndex1 = new CellIndex(1, 1);
        CellIndex cellIndex2 = new CellIndex(1, 1);

        // Act & Assert
        assertTrue(cellIndex1.equals(cellIndex2)); // Different objects, same values
    }

    @Test
    public void testEquals_DifferentRowOrCol() {
        // Arrange
        CellIndex cellIndex1 = new CellIndex(1, 1);
        CellIndex cellIndex2 = new CellIndex(2, 1);
        CellIndex cellIndex3 = new CellIndex(1, 2);

        // Act & Assert
        assertFalse(cellIndex1.equals(cellIndex2)); // Different row
        assertFalse(cellIndex1.equals(cellIndex3)); // Different column
    }

    @Test
    public void testEquals_NullAndDifferentClass() {
        // Arrange
        CellIndex cellIndex = new CellIndex(1, 1);

        // Act & Assert
        assertFalse(cellIndex.equals(null));          // Null comparison
        assertFalse(cellIndex.equals("String"));      // Different class comparison
    }

    @Test
    public void testHashCode_SameValues() {
        // Arrange
        CellIndex cellIndex1 = new CellIndex(1, 1);
        CellIndex cellIndex2 = new CellIndex(1, 1);

        // Act & Assert
        assertEquals(cellIndex1.hashCode(), cellIndex2.hashCode()); // Same values, same hashCode
    }

    @Test
    public void testHashCode_DifferentValues() {
        // Arrange
        CellIndex cellIndex1 = new CellIndex(1, 1);
        CellIndex cellIndex2 = new CellIndex(2, 2);

        // Act & Assert
        assertNotEquals(cellIndex1.hashCode(), cellIndex2.hashCode()); // Different values, different hashCode
    }

    @Test
    public void testGetIndex() {
        // Arrange
        CellIndex cellIndex = new CellIndex(3, 4);

        // Act
        CellIndex result = cellIndex.getIndex();

        // Assert
        assertSame(cellIndex, result); // Ensures the same object is returned
    }
}
