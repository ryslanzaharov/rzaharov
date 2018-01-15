package ru.job4j.multithreading.threads;

import java.io.*;

public class CountChar implements Runnable {

    private long count;

    @Override
    public void run() {
        BufferedReader br;
        StringBuilder sb = new StringBuilder();
        try {
            br = new BufferedReader(new FileReader(new File("D:\\t.txt")));
            while (br.ready()) {
                if (Thread.currentThread().isInterrupted())
                    throw new InterruptedException();
                count = sb.append(br.readLine()).length();
            }
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        } catch (FileNotFoundException fnfe) {
            fnfe.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public long getCount() {
        return count;
    }
}
