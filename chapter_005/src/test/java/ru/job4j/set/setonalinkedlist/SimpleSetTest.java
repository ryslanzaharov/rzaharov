package ru.job4j.set.setonalinkedlist;

import org.junit.Test;
import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;
import java.util.Iterator;

public class SimpleSetTest {

    @Test
    public void when() {
        SimpleSet<String> simpleSet = new SimpleSet<String>();
        simpleSet.add("value1");
        simpleSet.add("value2");
        simpleSet.add("value3");
        simpleSet.add("value1");
        simpleSet.add("value2");
        simpleSet.add("value3");
        Iterator iterator = simpleSet.iterator();
        iterator.next();
        iterator.next();
        assertThat(iterator.next(), is("value3"));
    }
}
