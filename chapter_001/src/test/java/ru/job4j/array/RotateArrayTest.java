package ru.job4j.array;

import org.junit.Test;
import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

public class RotateArrayTest {
    @Test
    public void whenRotateTwoRowTwoColArrayThenRotatedArray() {
        //напишите здесь тест, проверяющий поворот массива размером 2 на 2.
        RotateArray rotateArray = new RotateArray();
        int[][] result = rotateArray.rotate(new int[][]{{1, 2}, {3, 4}});
        int[][] expected = new int[][]{{3, 1}, {4, 2}};
        assertThat(result, is(expected));
    }

    @Test
    public void whenRotateThreeRowThreeColArrayThenRotatedArray() {
        //напишите здесь тест, проверяющий поворот массива размером 3 на 3.
        RotateArray rotateArray = new RotateArray();
        int[][] result = rotateArray.rotate(new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}});
        int[][] expected = new int[][]{{7, 4, 1}, {8, 5, 2}, {9, 6, 3}};
        assertThat(result, is(expected));
    }
}
