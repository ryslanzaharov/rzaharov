package ru.job4j.multithreading.threads;

public class NumberWords implements Runnable {

    private String text;
    private int numbWords;
    Thread thread;

    public NumberWords(String text) {
        this.text = text;
        thread = new Thread(this);
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {}
    }

    @Override
    public void run() {
        this.numbWords = text.split(" +").length;
    }

    public int getNumbWords() {
        return numbWords;
    }
}
