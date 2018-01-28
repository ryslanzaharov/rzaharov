package ru.job4j.multithreading.wait_notify_notifyall;

import java.util.ArrayList;
import java.util.List;

public class Amount {

    private int n = 0;
    private String[] quantity = new String[10];

    public synchronized void put() throws InterruptedException{
        while (n == 10) {
            wait();
        }
        quantity[n] = "value " + n;
        n++;
        notifyAll();
    }

    public synchronized String get() throws InterruptedException{
        while (n == 0) {
            wait();
        }
        n--;
        notifyAll();
        return quantity[n];
    }
}
