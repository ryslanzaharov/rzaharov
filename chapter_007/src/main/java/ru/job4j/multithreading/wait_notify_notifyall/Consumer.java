package ru.job4j.multithreading.wait_notify_notifyall;

public class Consumer implements Runnable {

    private Amount amount;

    public Consumer(Amount amount) {
        this.amount = amount;
    }
    @Override
    public void run() {
    while (true) {
        try {
            amount.get();
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    }
}
