package ru.job4j.multithreading.threads;

public class Time implements Runnable{

    private long time = 1000;

    @Override
    public void run() {
        System.out.println("Начало программы");
        try {
            Thread.sleep(time);
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        }
        System.out.println("Конец програмы");
    }

}
