package ru.job4j.multithreading.wait_notify_notifyall;

public class Producer implements Runnable{

    Amount amount;
    private int i;

    public Producer(Amount amount) {
        this.amount = amount;
    }

    @Override
    public void run() {
    while (true) {
        try {
            amount.put();
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    }
}
