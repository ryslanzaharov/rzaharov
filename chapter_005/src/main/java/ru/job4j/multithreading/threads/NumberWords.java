package ru.job4j.multithreading.threads;

public class NumberWords implements Runnable {

    private String text;
    private int numbWords;
    Thread thread;

    public NumberWords(String text) {
        this.text = text;
    }

    public void newThread() {
        thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run() {
        for (String word : text.split(" +")) {
            numbWords++;
        }
    }

    public int getNumbWords() {
        return numbWords;
    }

    public Thread getThread() {
        return thread;
    }
}
