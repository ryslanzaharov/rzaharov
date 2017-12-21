package ru.job4j.multithreading.threads;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class NumberWordSpacesTest {

    @Test
    public void whenConsiderTheNumberOfWordsAndSpacesInTheText() {
        String text = "werfw wefw rthr  rthr ";
        NumberWords firstThread = new NumberWords(text);
        NumberSpaces secondThread = new NumberSpaces(text);
        try {
         firstThread.thread.join();
         secondThread.thread.join();
        }catch (InterruptedException e) {
            e.printStackTrace();
        }

        assertThat(firstThread.getNumbWords(), is(4));
        assertThat(secondThread.getNumbSpaces(), is(5));

    }

}