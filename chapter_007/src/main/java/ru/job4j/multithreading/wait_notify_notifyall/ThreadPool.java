package ru.job4j.multithreading.wait_notify_notifyall;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class ThreadPool {

    private final Queue<Runnable> workQueue = new ConcurrentLinkedQueue<>();
    private volatile boolean isRun = true;
    private final Object lock = new Object();

    public ThreadPool(int nThread) {
        for (int i = 0; i < nThread; i++) {
            new Thread(new Work()).start();
        }
    }

    public void add(Runnable runnable) {
        synchronized (lock) {
            workQueue.offer(runnable);
        }
    }

    public void shutdown() {
        this.isRun = false;
    }

    private final class Work implements Runnable{

        @Override
        public void run() {
            while (isRun) {
                Runnable runnable = workQueue.poll();
                if (runnable != null) {
                    runnable.run();
                }
            }
        }
    }
}
