package ru.job4j.multithreading.jmm;
import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

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
