package ru.job4j;

import org.junit.Test;
import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

public class CheckingTest {
    @Test
    public void whenChecking() {
        Checking checking = new Checking();
        boolean result = checking.contains("laprivpretla", "pre");
        boolean expect = true;
        assertThat(result, is(expect));

    }
}
