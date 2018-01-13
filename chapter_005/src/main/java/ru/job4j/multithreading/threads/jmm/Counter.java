package ru.job4j.multithreading.threads.jmm;

import javax.annotation.concurrent.GuardedBy;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
public class Counter implements Runnable{
    @GuardedBy("this")
    private long count = 0;

    public void incCount() {
        count++;
    }

    public long getCount() {
        return count;
    }

    @Override
    public void run() {
        incCount();
    }
}
