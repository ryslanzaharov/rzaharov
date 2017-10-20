package ru.job4j.list;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

public class DynamicArrayTest {
    @Test
    public void whenAddLotOfElements() {
        DynamicArray<String> dynamicArray = new DynamicArray<>();
        List<String> expected = new ArrayList<>(
                Arrays.asList(
                        "value1", "value2", "value3", "value4"
                )
        );
        dynamicArray.add(expected.get(0));
        dynamicArray.add(expected.get(1));
        dynamicArray.add(expected.get(2));
        dynamicArray.add(expected.get(3));

        //compare the item
        assertThat(dynamicArray.get(0), is(expected.get(0)));

        //compare the elements
        assertThat(dynamicArray.getAll(), is(expected.toArray()));
    }
}
