package ru.job4j.iterator.converter;

import org.junit.Test;

import java.util.*;
import java.util.function.Consumer;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class ConverterTest {
    @Test
    public void whenItHasTwoInnerIt() {
        Iterator<Integer> it1 = Arrays.asList(
                4, 2, 0, 4, 6, 4, 9
        ).iterator();
        Iterator<Integer> it2 = Arrays.asList(
                0, 9, 8, 7, 5
        ).iterator();
        Iterator<Integer> it3 = Arrays.asList(
                1, 3, 5, 6, 7, 0, 9, 8, 4
        ).iterator();
        Iterator<Iterator<Integer>> it = Arrays.asList(
                it1, it2, it3

        ).iterator();
        Iterator<Integer> convert = new Converter().convert(it);
        List<Integer> expected = new ArrayList<>(
                Arrays.asList(
                        4, 2, 0, 4, 6, 4, 9, 0, 9, 8, 7, 5, 1, 3, 5, 6, 7, 0, 9, 8, 4
                )
        );
        List<Integer> result = new ArrayList<>();
        while(convert.hasNext()) {
            result.add(convert.next());
        }
        assertThat(result, is(expected));
    }
}