package ru.job4j.chess;

import java.util.Arrays;

public class Cell {

    private int col;
    private int row;


    public Cell(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public int getCol() {
        return col;
    }


    public int getRow() {
        return row;
    }
}
