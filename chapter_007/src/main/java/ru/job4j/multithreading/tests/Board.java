package ru.job4j.multithreading.tests;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Класс описывает поле.
 * @author Ryslan Zaharov (mailto:Ryslan8906137@yandex.ru).
 * @version 01.
 * @since 25.02.18.
 */

public class Board implements Runnable{
    //Двумерный массив для блокирования клетки в поле.
    private final ReentrantLock[][] board = new ReentrantLock[8][8];

    //инициализация поля.
    public void initBoard() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] = new ReentrantLock();
            }
        }
    }

  public ReentrantLock[][] getBoard() {
    return board;
  }

    @Override
    public void run() {
        initBoard();
        board[2][3].lock();
        board[4][4].lock();
        board[1][3].lock();
        board[7][3].lock();
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        board[2][3].unlock();
        board[4][4].unlock();
        board[1][3].unlock();
        board[7][3].unlock();
    }
}
