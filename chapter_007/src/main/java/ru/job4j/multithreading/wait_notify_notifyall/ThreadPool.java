package ru.job4j.multithreading.wait_notify_notifyall;

import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;
import jdk.nashorn.internal.ir.RuntimeNode;

import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class ThreadPool {

    private final BlockingQueue<Runnable> blockingQueue = new LinkedBlockingQueue<>();
    private final int nThread;

    public ThreadPool(int nThread) {
        this.nThread = nThread;
    }

    public void start() {
        for (int i = 0; i < this.nThread; i++) {
            new Thread(new Work()).start();
        }
    }

    public void add(Runnable runnable) throws InterruptedException{
        blockingQueue.put(runnable);
    }

    private final class Work implements Runnable{

        @Override
        public void run() {
            try {
                Runnable runnable;
                while ((runnable = blockingQueue.take()) != null) {
                    runnable.run();
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
