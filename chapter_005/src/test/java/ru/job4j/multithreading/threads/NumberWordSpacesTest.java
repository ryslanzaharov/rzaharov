package ru.job4j.multithreading.threads;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class NumberWordSpacesTest {

    @Test
    public void whenConsiderTheNumberOfWordsAndSpacesInTheText() {
        System.out.println("Начало программы");
        String text = "werfw wefw rthr  rthr ";
        NumberWords firstThread = new NumberWords(text);
        NumberSpaces secondThread = new NumberSpaces(text);
        firstThread.newThread();
        secondThread.newThread();
        try {
         firstThread.getThread().join();
         secondThread.getThread().join();
        }catch (InterruptedException e) {
            e.printStackTrace();
        }

        assertThat(firstThread.getNumbWords(), is(4));
        assertThat(secondThread.getNumbSpaces(), is(5));
        System.out.println("Конец программы");
    }

}