package ru.job4j.set;

import org.junit.Test;
import ru.job4j.set.setonaarrays.SimpleSet;

import java.util.Iterator;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;
public class SimpleSetTest {

    @Test
    public void whenAddDifferentElements() {
        SimpleSet<String> simpleSet = new SimpleSet<>();
        simpleSet.add("value2");
        simpleSet.add("value2");
        simpleSet.add("value3");
        simpleSet.add("value1");
        simpleSet.add("value4");
        simpleSet.add("value5");
        simpleSet.sort();
        Iterator iterator = simpleSet.iterator();
        iterator.next();
        iterator.next();
        assertThat(iterator.next(), is("value3"));
    }

}