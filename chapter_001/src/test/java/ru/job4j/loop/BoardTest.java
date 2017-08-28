package ru.job4j.loop;

import org.junit.Test;
import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

public class BoardTest {
    @Test
    public void whenChessBoard() {
        Board board = new Board();
        String result = board.paint(5, 4);
        String expected = "x x x\n" +
                          " x x \n" +
                          "x x x\n" +
                          " x x \n";
        assertThat(result, is(expected));
    }
}
