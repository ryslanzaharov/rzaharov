package ru.job4j.multithreading.lift;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Класс ввода.
 * @author Ryslan Zaharov (mailto:Ryslan8906137@yandex.ru).
 * @version 01.
 * @since 09.03.18.
 */

public class Input implements Runnable{

    private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private int numbFloors;
    private int numbEl = 1;
    //определяет получен ли лифт
    private boolean isElevator = false;

    public void setElevator(boolean elevator) {
        isElevator = elevator;
    }
    //этаж вводимый в лифте
    private int floor;

    private BlockingQueue<Integer> queue = new ArrayBlockingQueue<Integer>(1);

    public Input(int numbFloors) {
        this.numbFloors = numbFloors;
    }
    @Override
    public void run() {
        try{
            queue.put(entrance());
            while(true) {
                if (isElevator) {
                    queue.put(elevator());
                    break;
                }
                Thread.yield();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    //при нахождении в подъезде
    public int entrance()  {
        try {
            System.out.println("Введите номер Вашего этажа");
            numbEl = Integer.parseInt(br.readLine());
            if (numbEl < 0 || numbEl > numbFloors) {
                System.out.println("Такого этажа не существует");
                entrance();
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return numbEl;
    }

    //при нахождении в лифте
    public int elevator() {
        try {
            System.out.println("Введите номер нужного Вам этажа");
            this.floor = Integer.parseInt(br.readLine());
            if (floor < 1 || floor > numbFloors) {
                System.out.println("Такого этажа не существует");
                elevator();
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (isElevator)
            isElevator = false;
        else
            isElevator = true;
        return floor;
    }

    public BlockingQueue<Integer> getQueue() {
        return queue;
    }
}
