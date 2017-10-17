package ru.job4j.generic;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

public class SimpleArrayTest {
    @Test
    public void whenUseGenericsInTheContainer() {
        SimpleArray<String> simpleArray = new SimpleArray<>(5);
        simpleArray.add("value1");
        simpleArray.add("value2");
        simpleArray.add("value3");
        simpleArray.add("value4");
        simpleArray.add("value5");
        assertThat(simpleArray.get(1), is("value2"));
        simpleArray.update(1, "newvalue2");
        assertThat(simpleArray.get(1), is("newvalue2"));
        simpleArray.delete(4);
        assertThat(Arrays.toString(simpleArray.getObjects()), is("[value1, newvalue2, value3, value4, value5]"));
    }

}