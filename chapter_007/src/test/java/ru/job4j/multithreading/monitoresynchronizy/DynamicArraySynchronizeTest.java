package ru.job4j.multithreading.monitoresynchronizy;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class DynamicArraySynchronizeTest {
    @Test
    public void whenAddLotOfElements() throws InterruptedException{
        DynamicArraySynchronize<String> dynamicArray = new DynamicArraySynchronize<>();
        List<String> expected = new ArrayList<>(
                Arrays.asList(
                        "value1", "value2", "value3", "value4"
                )
        );
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                    try {
                        dynamicArray.add(expected.get(0));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
            }
        });
        t1.start();
        t1.join();

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                 try {
                        dynamicArray.add(expected.get(1));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
            }
        });
        t2.start();
        t2.join();

        Thread t3 = new Thread(new Runnable() {
            @Override
            public void run() {
                 try {
                        dynamicArray.add(expected.get(2));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
            }
        });
        t3.start();
        t3.join();

        Thread t4 = new Thread(new Runnable() {
            @Override
            public void run() {
                 try {
                        dynamicArray.add(expected.get(3));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
            }
        });
        t4.start();
        t4.join();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //compare the item
        assertThat(dynamicArray.get(0), is(expected.get(0)));
        assertThat(dynamicArray.get(1), is(expected.get(1)));
        assertThat(dynamicArray.get(2), is(expected.get(2)));
        assertThat(dynamicArray.get(3), is(expected.get(3)));

        //compare the elements
        assertThat(dynamicArray.getAll(), is(expected.toArray()));
    }

}