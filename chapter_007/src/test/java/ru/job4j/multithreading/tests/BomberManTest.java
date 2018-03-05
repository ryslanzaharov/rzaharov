package ru.job4j.multithreading.tests;

import org.junit.Test;

import javax.naming.Name;

/**
 * @author Ryslan Zaharov (mailto:Ryslan8906137@yandex.ru).
 * @version 01.
 * @since 25.02.18.
 */

public class BomberManTest {

    @Test
    public void whenTheHeroWalksTheFieldRandomly() {

        Hero bomberMan = new Hero(Names.BomberMan);
        Hero monster = new Hero(Names.Monster);
        Board board = new Board();
        bomberMan.setBoard(board);
        monster.setBoard(board);
        Thread thBoard = new Thread(board);
        thBoard.start();
        Thread thMonster = new Thread(monster);
        Thread th = new Thread(bomberMan);
        try {
           // thBoard.join();
            th.start();
            thMonster.start();
            Thread.sleep(10000);
            th.interrupt();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}