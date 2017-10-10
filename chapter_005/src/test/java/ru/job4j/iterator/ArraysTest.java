package ru.job4j.iterator;

import org.junit.Test;
import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

public class ArraysTest {

    @Test
    public void whenGetNextCallShouldPointerForward() {
        Integer [][] arrayInt = new Integer[][]{{1, 2}, {3, 4}};
        Arrays<Integer> it = new Arrays<Integer>(arrayInt);
        Integer[] result = new Integer[4];
        int i = 0;
        for (Integer n : it) {
            result[i++] = n;
        }
        Integer [] expected = {1, 2, 3, 4};
        assertThat(result, is(expected));
    }
}
