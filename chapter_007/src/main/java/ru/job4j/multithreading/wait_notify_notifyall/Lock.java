package ru.job4j.multithreading.wait_notify_notifyall;

public class Lock {

    private boolean locked = false;
    private final Object lock = new Object();

    public void lock() throws InterruptedException{
        synchronized (lock) {
            while(locked) {
                lock.wait();
            }
            locked = true;
        }

    }

    public void unlock() throws InterruptedException{
        synchronized (lock) {
            locked = false;
            lock.notifyAll();
        }
    }
}
