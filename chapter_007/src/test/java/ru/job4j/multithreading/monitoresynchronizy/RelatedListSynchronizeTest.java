package ru.job4j.multithreading.monitoresynchronizy;

import org.junit.Test;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

public class RelatedListSynchronizeTest {

    @Test
    public void whenAddElementsToTheLinkedListThreadSafe() throws InterruptedException{
        RelatedListSynchronize<String> rls = new RelatedListSynchronize<>();
               Thread t1 = new Thread(new Runnable() {
                    @Override
                    public void run() {

                        rls.add("value1");

                    }
                });
               t1.start();
               t1.join();

            Thread t2 = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        rls.add("value2");
                    }
                });
            t2.start();
            t2.join();

            Thread t3 = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        rls.add("value3");
                    }
                });
            t3.start();
            t3.join();

            Thread t4 = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        rls.add("value4");

                    }
                });
            t4.start();
            t4.join();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        assertThat(rls.get(0), is("value1"));
        assertThat(rls.get(1), is("value2"));
        assertThat(rls.get(2), is("value3"));
        assertThat(rls.get(3), is("value4"));
    }
}