package ru.job4j.iterator.iteratorevennumbers;

import org.junit.Test;
import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

import java.util.Iterator;

public class EventItTest {

    @Test
    public void whenIteratorEvenNumbers() {
        Iterator it = new EventIt(new int[] {4, 2, 1, 1, 6, 8});
        it.next();
        it.next();
        it.next();
        int expected = (Integer) it.next();
        int result = 8;
        assertThat(result, is(expected));
    }
}