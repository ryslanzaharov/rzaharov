package ru.job4j.test;

import org.junit.Test;

import java.util.HashMap;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class WordsTest {

    @Test
    public void whenComparisonTest() {
        Words words = new Words("value");
        Words words1 = new Words("lueav");
        HashMap<Words, Object> map = new HashMap<>();
        map.put(words, new Object());
        map.put(words1, new Object());
        assertThat(map.size(), is(2));
    }
}
