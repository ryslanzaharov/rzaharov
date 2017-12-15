package ru.job4j.test;

import org.junit.Test;

import java.util.Calendar;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class OrderBookTest {

    @Test
    public void whenUseOrderBook() {
        long startTime = System.currentTimeMillis();
        OrderBook orderBook = new OrderBook();
        orderBook.parsing();
        orderBook.aggregated();
        orderBook.matching();
        long totalTime = System.currentTimeMillis() - startTime;
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis( totalTime );
        int seconds = cal.get(Calendar.SECOND);
        assertThat(seconds < 6, is(true) );
    }

}
