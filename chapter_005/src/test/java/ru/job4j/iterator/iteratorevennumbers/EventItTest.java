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
    @Test
    public void evenIteratorShouldReturnEvenNumbersSequentially(){
        EventIt it = new EventIt(new int[]{1,2,3,4,5,6,7,9});
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(2));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(4));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(6));
        assertThat(it.hasNext(), is(false));
    }
    @Test public void checkThatHasNextDoesntAffect () {
        EventIt it = new EventIt(new int[]{1, 2, 4, 6});
        assertThat(it.hasNext(), is(true));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(2));
        assertThat(it.next(), is(4));
        assertThat(it.next(), is(6));
    }

}