package ru.job4j.test;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class ComparisonWordsTest {
    @Test
    public void whenComparisonWords() {
        ComparisonWords comparisonWords = new ComparisonWords();
        boolean result = comparisonWords.containsLetter("valte", "eulav");
        assertThat(result, is(false));
    }

}