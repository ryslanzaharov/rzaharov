package ru.job4j.set.simplehashset;

import org.junit.Test;
import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

public class SimpleHashSetTest {
    @Test
    public void whenAddDifferentsElementsByHashCode() {
        SimpleHashSet<Integer> integerSimpleHashSet = new SimpleHashSet<>(10);
        integerSimpleHashSet.add(1);
        integerSimpleHashSet.add(2);
        integerSimpleHashSet.add(3);
        integerSimpleHashSet.add(4);
        integerSimpleHashSet.add(5);
        integerSimpleHashSet.add(6);
        integerSimpleHashSet.add(7);
        integerSimpleHashSet.add(8);
        integerSimpleHashSet.add(9);
        integerSimpleHashSet.add(10);
        boolean result = integerSimpleHashSet.add(2);
        assertThat(result, is(false));
    }

    @Test
    public void whenDeleteElementsByHashCode() {
        SimpleHashSet<Integer> integerSimpleHashSet = new SimpleHashSet<>(7);
        integerSimpleHashSet.add(1);
        integerSimpleHashSet.add(2);
        integerSimpleHashSet.add(3);
        integerSimpleHashSet.add(4);
        integerSimpleHashSet.add(1111);
        integerSimpleHashSet.add(5);
        integerSimpleHashSet.add(6);
        integerSimpleHashSet.add(1111);
        integerSimpleHashSet.remove(2);
        Object expected = null;
        assertThat(integerSimpleHashSet.keys[2], is(expected));
    }

}