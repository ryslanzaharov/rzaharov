package ru.job4j.test;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class WordsContainsTest {

    @Test
    public void whenComparisonTest() {
        WordsContains wordsContains = new WordsContains();
        boolean result = wordsContains.containsLetter("leffave", "fevaelf");
        assertThat(result, is(true));
    }
}
