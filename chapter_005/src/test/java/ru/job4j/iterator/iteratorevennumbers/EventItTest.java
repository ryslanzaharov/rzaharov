package ru.job4j.iterator.iteratorevennumbers;

import org.junit.Test;
import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class EventItTest {

    @Test
    public void whenIteratorEvenNumbers() {
        Iterator it = new EventIt(new int[] {4, 2, 1, 1});
        it.next();
        int expected = (Integer) it.next();
        int result = 2;
        assertThat(result, is(expected));
    }
}