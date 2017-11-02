package ru.job4j.list.node;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

public class NodeTest {
    @Test
    public void whenCheckTheClosuresInTheLinkedList() {
        Node<Integer> first = new Node<>(1);
        Node<Integer> two = new Node<>(2);
        Node<Integer> third = new Node<>(3);
        Node<Integer> four = new Node<>(4);
        first.next = two;
        two.next = third;
        third.next = four;
        four.next = first;
        Node.Cycle cycle = new Node.Cycle();
        boolean result = cycle.hasCycle(first);
        assertThat(result, is(true));

    }

}