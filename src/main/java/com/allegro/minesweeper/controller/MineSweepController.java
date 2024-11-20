package com.allegro.minesweeper.controller;

public interface MineSweepController {

    public void createGrid(int size);

    public void setMines(int noOfMines);

    public void revealCell(String cell);
}
