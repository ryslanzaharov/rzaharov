package ru.job4j.multithreading.tests;

import org.junit.Test;

/**
 * @author Ryslan Zaharov (mailto:Ryslan8906137@yandex.ru).
 * @version 01.
 * @since 25.02.18.
 */

public class BomberManTest {

    @Test
    public void whenTheHeroWalksTheFieldRandomly() {
        BomberMan bomberMan = new BomberMan(2, 3);
        Board board = new Board();
        bomberMan.setBoard(board);
        Thread thBoard = new Thread(board);
        thBoard.start();
        Thread th = new Thread(bomberMan);
        try {
            thBoard.join();
            th.start();
            Thread.sleep(10000);
            th.interrupt();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}