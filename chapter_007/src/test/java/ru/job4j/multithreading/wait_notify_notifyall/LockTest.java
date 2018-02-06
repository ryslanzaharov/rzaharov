package ru.job4j.multithreading.wait_notify_notifyall;

import org.junit.Test;

public class LockTest {

    @Test
    public void whenBlockObjectThroughTheClassLocks() {
        Lock lock = new Lock();
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    lock.lock();
                for (int i = 0 ; i < 5; i++) {
                    System.out.println(i);
                    Thread.sleep(10);
                }
                lock.unlock();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    lock.lock();
                    for (int i = 5 ; i < 10; i++) {
                        System.out.println(i);
                        Thread.sleep(10);
                    }
                    lock.unlock();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        thread2.start();
        thread1.start();
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}