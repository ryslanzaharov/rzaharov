package ru.job4j.convert;

import org.junit.Test;
import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

import java.util.ArrayList;
import java.util.List;

public class ConvertListTest {
    @Test
    public void whenToList() {
        ConvertList convertList = new ConvertList();
        int[][] array = {{1, 2, 3}, {4, 5, 6}, {7, 0 ,0}};
        List<Integer> result = convertList.toList(array);
        List<Integer> expected = new ArrayList<>();
        expected.add(1);
        expected.add(2);
        expected.add(3);
        expected.add(4);
        expected.add(5);
        expected.add(6);
        expected.add(7);
        assertThat(result, is(expected));
    }

    @Test
    public void whenToArray() {
        ConvertList convertList = new ConvertList();
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(6);
        list.add(7);
        int[][] result = convertList.toArray(list, 3);
        int[][] expected = {{1, 2, 3}, {4, 5, 6}, {7, 0 ,0}};
        assertThat(result, is(expected));
    }
}
