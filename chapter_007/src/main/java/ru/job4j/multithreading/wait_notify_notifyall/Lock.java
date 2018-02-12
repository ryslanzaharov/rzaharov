package ru.job4j.multithreading.wait_notify_notifyall;

public class Lock {

    public volatile boolean locked = false;
    private final Object lock = new Object();
    private Thread threadLock = null;

    public void lock() throws InterruptedException{
        synchronized (lock) {
            while(locked && threadLock != Thread.currentThread()) {
                lock.wait();
            }
            locked = true;
            threadLock = Thread.currentThread();
        }

    }

    public void unlock() throws InterruptedException {
        synchronized (lock) {
        if (Thread.currentThread() == threadLock) {
                locked = false;
                lock.notifyAll();
            }
        }
    }
}
