package ru.job4j.map;

import org.junit.Test;

import java.util.Iterator;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class DirectoryTest {

    @Test
    public void whenAddRemoveAndGetTheElementsToTheList() {
        Directory<Integer, String> directory = new Directory<>();
        //Add the elements.
        directory.insert(null, "value0");
        directory.insert(2, "value1");
        directory.insert(3, "value2");
        directory.insert(3, "value3");
        directory.insert(4, "value4");
        directory.insert(6, "value6");
        directory.insert(7, "value7");
        directory.insert(8, "value8");
        //Get the elements.
        String result = directory.get(3);
        assertThat( result, is("value2"));
        //Remove the elements
        directory.delete(3);
        String expected = null;
        assertThat(directory.get(3), is(expected));
    }

}