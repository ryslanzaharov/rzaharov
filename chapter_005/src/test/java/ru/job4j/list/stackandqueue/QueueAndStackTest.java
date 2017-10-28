package ru.job4j.list.stackandqueue;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class QueueAndStackTest {
    @Test
    public void whenPushAndPollSimpleStack() {
        SimpleStack<String> list = new SimpleStack<String>();
        list.push("value0");
        list.push("value1");
        list.push("value2");
        list.push("value3");
        list.poll();
        list.poll();
        assertThat("value1", is(list.poll()));
    }

    @Test
    public void whenPushAndPollUseSimpleQueue() {
        SimpleQueue<String> list = new SimpleQueue<>();
        list.push("value0");
        list.push("value1");
        list.push("value2");
        list.push("value3");
        list.poll();
        assertThat("value1", is(list.poll()));
    }
}
