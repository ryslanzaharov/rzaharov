package ru.job4j.convert;

import org.junit.Test;
import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ConvertListTest {

    @Test
    public void whenToList() {
        ConvertList convertList = new ConvertList();
        int[][] array = {{1, 2, 3}, {4, 5, 6}, {7, 0 ,0}};
        List<Integer> result = convertList.toList(array);
        List<Integer> expected = new ArrayList<>();
        expected.addAll(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 0, 0));
        assertThat(result, is(expected));
    }

    @Test
    public void whenToArray() {
        ConvertList convertList = new ConvertList();
        List<Integer> list = new ArrayList<>();
        list.addAll(Arrays.asList(1, 2, null, 3, 4, 5, 6, 7));
        int[][] result = convertList.toArray(list, 3);
        int[][] expected = {{1, 2, 3}, {4, 5, 6}, {7, 0 ,0}};
        assertThat(result, is(expected));
    }
}
