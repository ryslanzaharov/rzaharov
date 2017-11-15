package ru.job4j.map;

import org.junit.Test;

import java.util.Iterator;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class DirectoryTest {

    @Test
    public void whenAddRemoveAndGetTheElementsToTheList() {
        Directory<Integer, String> directory = new Directory<>(5);
        //Add the elements.
        directory.insert(2, "value1");
        directory.insert(3, "value2");
        directory.insert(3, "value3");
        directory.insert(4, "value4");
        Iterator iterator =  directory.iterator();
        iterator.next();
        iterator.next();
        assertThat(iterator.next(), is("value4"));
        //Get the elements.
        String result = directory.get(3);
        assertThat(result, is("value2"));
        //Remove the elements
        directory.delete(3);
        String expected = null;
        assertThat(directory.get(3), is(expected));
    }

}