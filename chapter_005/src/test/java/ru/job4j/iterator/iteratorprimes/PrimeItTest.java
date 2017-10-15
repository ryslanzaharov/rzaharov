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


}