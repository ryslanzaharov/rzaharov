package ru.job4j.multithreading.threads;

public class Start {

    public static void main(String[] args) throws InterruptedException{
        CountChar countChar = new CountChar();
        Time time = new Time();
        Thread timeThread = new Thread(time);
        timeThread.start();
        Thread countThread = new Thread(countChar);
        countThread.start();
            while (countThread.isAlive()) {
                if (!timeThread.isAlive()) {
                    countThread.interrupt();
                }
            }
        System.out.println(countChar.getCount());

    }
}
