package ru.job4j.array;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.hamcrest.collection.IsArrayContainingInAnyOrder.arrayContainingInAnyOrder;

public class ArrayCombiningTest {
    @Test
    public void whenArrayCombining() {
        ArrayCombining arrayCombining = new ArrayCombining();
        int[] mas1 = {1, 3, 6, 7, 10};
        int[] mas2 = {2, 3, 4, 5, 8, 9, 10, 11};
        int[] result = arrayCombining.combination(mas1, mas2);
        int[] expect = {1, 2, 3, 3, 4, 5, 6, 7, 8, 9, 10, 10, 11};
        assertThat(result, is(expect));
    }
}
