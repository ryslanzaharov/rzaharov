package ru.job4j.loop;

public class Board {
    public String paint(int width, int height) {
        StringBuilder board = new StringBuilder();
        for (int i = 0; i < height; i++){
            for (int n = 0; n < width; n++){
                if (i % 2 == 0) {
                    if (n % 2 == 0)
                        board.append("x");
                    else board.append(" ");
                }
                else {
                    if (n % 2 == 0)
                        board.append(" ");
                    else
                        board.append("x");
                }
                if (n==width-1)
                    board.append("\n");
            }
        }
        return board.toString();
    }
}
