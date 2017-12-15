package ru.job4j.test;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class WordsTest {

    @Test
    public void whenComparisonTest() {
        Map<Words, Object> map = new HashMap<>();
        map.put(new Words("value"), new Object());
        map.put(new Words("lueav"), new Object());
        assertThat(map.size(), is(1));
    }
}
