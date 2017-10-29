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
        List<Node> list = new ArrayList<>(
                Arrays.asList(
                      first, two, third, four
                )
        );
        Node node = new Node(list);
        boolean result = node.hasCycle();
        assertThat(result, is(true));
    }

}