package ru.job4j.iterator.iteratorprimes;

import static org.junit.Assert.*;
import org.junit.Test;
import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

import java.util.Iterator;

public class PrimeItTest {
    @Test
    public void whenChooseSimpleNumbers() {
        Iterator it = new PrimeIt(new int[]{3, 4, 5, 6, 7});
        it.next();
        it.next();
        int result = (int)it.next();
        int expected = 7;
        assertThat(result, is(expected));
    }

    @Test
    public void evenIteratorShouldReturnPrimeNumbersSequentially(){
        PrimeIt it = new PrimeIt(new int[]{1,2,3,4,5,6,7,9});
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(2));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(3));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(5));
        assertThat(it.next(), is(7));
        assertThat(it.hasNext(), is(false));
       // it.next();
        System.out.println(it.hasNext());
    }

    @Test
    public void checkThatHasNextDoesntAffect () {
        PrimeIt it = new PrimeIt(new int[]{2, 3, 4, 5, 6, 7, 12, 3571, 4});
        assertThat(it.hasNext(), is(true));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(2));
        assertThat(it.next(), is(3));
        assertThat(it.next(), is(5));
        assertThat(it.next(), is(7));
        assertThat(it.next(), is(3571));
    }


}