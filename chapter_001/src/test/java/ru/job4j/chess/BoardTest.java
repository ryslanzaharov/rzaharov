package ru.job4j.chess;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class BoardTest {
    @Test
    public void whenRowAndColumnHaveName() {
        Board board = new Board();
        String expected = "e5";
        String result = board.nameRowAndColumn(5, 5);
        assertThat(result, is(expected));
    }


    @Test
    public void whenGetCellByCellName() {
        Board board = new Board();
        String expected = "31";
        String result = board.getCellByCellName("b3");
        assertThat(result, is(expected));
    }


    @Test
    public void whenTheCorrectCell() throws Exception {
        Board board = new Board();
        Cell source = new Cell(4, 7);
        Cell destination = new Cell(1, 4);
        board.cells[4][7] = new Bishop(source);
        assertThat(board.move(source, destination), is(true));
    }

    @Test
    public void whenSetRightDestinationCellThenGetFigureInTheDestinationCell() throws Exception {
        Board board = new Board();
        Cell source = new Cell(0, 7);
        Cell destination = new Cell(7, 0);
        Bishop bishop = new Bishop(source);
        board.cells[0][7] = bishop;
        board.move(source, destination);
        assertThat(board.cells[7][0] instanceof Bishop, is(true));
    }

    @Test
    public void whenSetRightDestinationCellThenGetRightFigureOnDestinationCell() throws Exception {
        Board board = new Board();
        Cell source = new Cell(1, 0);
        Cell destination = new Cell(7, 6);
        Bishop bishop = new Bishop(source);
        board.cells[1][0] = bishop;
        board.move(source, destination);
        assertThat(board.cells[7][6] instanceof Bishop, is(true));
    }

    @Test
    public void whenSetWrongDestinationCellThenGetImpossibleMoveException() throws Exception {
        Board board = new Board();
        Cell source = new Cell(5, 4);
        Cell destination = new Cell(7, 7);
        Bishop bishop = new Bishop(source);
        board.cells[5][4] = bishop;
        board.move(source, destination);

    }

    @Test
    public void whenSetWrongWayThenGetOccupiedWayException() throws Exception {
        Board board = new Board();
        Cell source = new Cell(1, 0);
        Cell destination = new Cell(7, 6);
        Bishop bishop1 = new Bishop(source);
        Bishop bishop2 = new Bishop(destination);
        board.cells[1][0] = bishop1;
        board.cells[7][6] = bishop2;
        board.move(source, destination);
    }

}
