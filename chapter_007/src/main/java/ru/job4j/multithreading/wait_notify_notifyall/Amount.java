package ru.job4j.multithreading.wait_notify_notifyall;

import java.util.ArrayList;
import java.util.List;

public class Amount {

    private volatile int n = 0;
    private boolean bound;

    private volatile String[] quantity = new String[10];

    public synchronized void put() throws InterruptedException{
        if (n == 10) {
            wait();
        }
        quantity[n] = "value " + n;
        n++;
        notify();
    }

    public synchronized String get() throws InterruptedException{
        if (n == 0) {
            wait();
        }
        n--;
        notify();
        return quantity[n];
    }
}
