package ru.job4j.multithreading.tests;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;


/**
 * Класс описывает поведение BomberMan.
 * @author Ryslan Zaharov (mailto:Ryslan8906137@yandex.ru).
 * @version 01.
 * @since 25.02.18.
 */

public class Hero implements Runnable{

    //Координаты массива в Board.
    private int x;
    private int y;

    //доступ к Board.
    private Board board;

    //доступ к ячейкам массива в Board.
    private ReentrantLock lock;

    private final Random random = new Random();

    //хранит значения для определения координат
    private int step;

    private int time;

    private Names name;

    public Hero(Names name) {
       this.name = name;
       this.x = random.nextInt(8);
       this.y = random.nextInt(8);
    }

    @Override
    public void run() {
        if (this.name.equals(Names.BomberMan)) {
            this.time = 500;
        }
        else {
            this.time = 5000;
        }
        //получаем замок из координат.
        this.lock = board.getBoard()[x][y];
        //пытаемся захватить.
        boolean ifLock = this.lock.tryLock();
        //если смогли захватить.
        if (ifLock)
            //пока не прервется нить.
        while(!Thread.currentThread().isInterrupted()){
            try {
                TimeUnit.SECONDS.sleep(1);
                if (this.name.equals(Names.BomberMan)) {
                    boolean kill = this.lock.hasQueuedThreads();
                    if (kill) {
                        System.out.println("Game over!");
                        break;
                    }
                }
                move();
            } catch (InterruptedException e) {
                System.out.println("Нить прервана!");
            }
        }
    }


    //устанавливаем board.
    public void setBoard(Board board) {
        this.board = board;
    }
    //делаем ход рандомно.
    public void steps() {
        step = random.nextInt(4);
        if (step == 0) {
            x++;
        }
        else if (step == 1) {
            x--;
        }
        else if (step == 2) {
            y++;
        }
        else if (step == 3) {
            y--;
        }
    }
    //ход назад.
    public void stepBack() {
        if (step == 2)
            y--;
        else if (step == 1)
            x++;
        else if (step == 0)
            x--;
        else if (step == 3)
            y++;
    }
    //проверяет выходят ли координаты x, y за пределы двумерного массива.
    public boolean goesAbroad() {
        boolean isGoesAbroad = false;
        if (x < 0 || y < 0 ||
                x >= board.getBoard().length
                || y >= board.getBoard().length)
            isGoesAbroad = true;
        return isGoesAbroad;
    }
    //метод осуществляет передвижение.
    public void move() throws InterruptedException {
        //если координаты не выходят за пределы массива.
        if (!goesAbroad()) {
            //получаем lock.
            ReentrantLock lock = board.getBoard()[x][y];
            //пытаемся захватить lock в течение 500 мс , иначе идем обратно и меняем координаты.
            boolean getLock = lock.tryLock(time, TimeUnit.MILLISECONDS);
            if (getLock) {
                this.lock.unlock();
                this.lock = lock;
                System.out.println(x + " " + y + " " + this.name);
                steps();

            } else {
                stepBack();
                steps();
            }
        }
        else {
            System.out.println("Выход за пределы поля!");
            stepBack();
        }
    }



}
