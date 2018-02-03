package ru.job4j.multithreading.wait_notify_notifyall;

import org.junit.Test;

import static org.junit.Assert.*;

public class ThreadPoolTest {

    @Test
    public void whenRunTasksUsingTheThreadPool() throws InterruptedException{
        ThreadPool threadPool = new ThreadPool(Runtime.getRuntime().availableProcessors());
        threadPool.add(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    System.out.println(i);
                }
            }
        });
        threadPool.add(new Runnable() {
            @Override
            public void run() {
                System.out.println("th1");
            }
        });
        threadPool.add(new Runnable() {
            @Override
            public void run() {
                System.out.println("th2");
            }
        });
        Thread.sleep(5000);
    }

}