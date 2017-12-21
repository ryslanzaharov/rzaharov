package ru.job4j.multithreading.threads;

public class NumberSpaces implements Runnable {

    private String text;
    private int numbSpaces;
    Thread thread;

    public NumberSpaces(String text) {
        this.text = text;
        thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run() {
        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) == 32)
                numbSpaces++;
        }
    }

    public int getNumbSpaces() {
        return numbSpaces;
    }
}
