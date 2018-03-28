package ru.job4j.multithreading.lift;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * Класс работы лифта.
 * @author Ryslan Zaharov (mailto:Ryslan8906137@yandex.ru).
 * @version 01.
 * @since 09.03.18.
 */

public class Lift implements Runnable{

    //кол-во этажей в подъезде.
    private final int numbFloors;
    //высота одного этажа.
    private final int heightFloor;
    //скорость лифта при движении в метрах в секунду.
    private final int speed;
    //время между открытием и закрытием дверей.
    private final int time;
    //этаж в подъезде и этаж в лифте
    private BlockingQueue<Integer> queue;
    //исходное положение лифта
    private int from = 1;

    private final Input input;
    //счетчик для лифта
    private int count = 0;

    public Lift(int numbFloors, int heightFloor, int speed, int time, Input input) {
        this.numbFloors = numbFloors;
        this.heightFloor = heightFloor;
        this.speed = speed;
        this.time = time;
        this.input = input;
    }

    @Override
    public void run() {
        int floor;
        try {
            queue = input.getQueue();
            callFromEntrance(queue.take());

        while (true) {
            if (count == 2) {
                break;
            }
            floor = queue.take();
            callFromEntrance(floor);
            TimeUnit.SECONDS.sleep(heightFloor / speed);
        }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //перемещение лифта
    public void callFromEntrance(int to) throws InterruptedException{
        while (from != to) {
            System.out.println("Лифт проезжает " + from + " этаж");
            if (to < from)
                from--;
            else if (to > from)
                from++;
            TimeUnit.SECONDS.sleep(heightFloor / speed);
        }
        openCloseElev();
    }

    //открытие и закрытие лифта
    public void openCloseElev() throws InterruptedException{
        count++;
        System.out.println("Лифт открывается");
        TimeUnit.SECONDS.sleep(time);
        System.out.println("Лифт закрывается");
        if (count == 1)
            input.setElevator(true);
        else {
            input.setElevator(false);
           // count = 0;
        }
    }

    public int getCount() {
        return count;
    }
}
