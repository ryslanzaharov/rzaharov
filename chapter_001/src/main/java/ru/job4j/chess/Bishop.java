package ru.job4j.chess;

public class Bishop extends Figure {

    public Bishop(Cell source) {
        super(source);
    }

    public Cell[] way(Cell dist) throws ImpossibleMoveException{
        int stepRow = 1;
        int stepCol = 1;
        int height = Math.abs(this.position.getRow() - dist.getRow());
        int width = Math.abs(this.position.getCol() - dist.getCol());
        Cell[] cells = new Cell[8];

        if (height == width) {
            if (dist.getRow() > this.position.getRow() && dist.getCol() < this.position.getCol()) {
                stepCol = -1;
            } else if (dist.getRow() < this.position.getRow() && dist.getCol() > this.position.getCol()) {
                stepRow = -1;
            } else if (dist.getRow() < this.position.getRow() && dist.getCol() < this.position.getCol()) {
                stepCol = -1;
                stepRow = -1;
            }
            int row = this.position.getRow();
            int col = this.position.getCol();

            for (int i = 0; i < height; i++) {
                cells[i] = new Cell(row += stepRow, col += stepCol);
            }
        } else {
            throw new ImpossibleMoveException("The Bishop can't go there.");
        }

        return cells;
    }
    public Bishop clone(Cell dist) {
        return new Bishop(dist);
    }
}
