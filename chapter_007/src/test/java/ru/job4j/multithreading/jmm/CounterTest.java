package ru.job4j.multithreading.jmm;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class CounterTest {

    @Test
    public void whenCountThread() throws InterruptedException{
        Counter counter = new Counter();
        for (int i = 0; i < 100000; i++) {
            Thread th = new Thread(counter);
            th.start();
        }
        Thread.sleep(10000);
        assertThat(counter.getCount() != 100000, is(true));
    }

}