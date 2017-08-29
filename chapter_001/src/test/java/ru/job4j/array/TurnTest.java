package ru.job4j.array;

import org.junit.Test;
import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

public class TurnTest {
    @Test
    public void whenTurnArrayWithEvenAmountOfElementsThenTurnedArray() {
        Turn turn = new Turn();
        int[] array = new int[]{1, 2, 3, 4, 5, 6};
        int[] result = turn.back(array);
        int[] expected = new int[]{6, 5, 4, 3, 2, 1};
        assertThat(result, is(expected));
    }
    @Test
    public void whenTurnArrayWithOddAmountOfElementsThenTurnedArray() {
        Turn turn = new Turn();
        int[] array = new int[]{1, 2, 3, 4, 5};
        int[] result = turn.back(array);
        int[] expected = new int[]{5, 4, 3, 2, 1};
        assertThat(result, is(expected));
    }
}
