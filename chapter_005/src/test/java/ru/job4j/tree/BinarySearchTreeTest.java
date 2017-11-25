package ru.job4j.tree;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class BinarySearchTreeTest {

    @Test
    public void whenAddElementsToTheTree() {
        BinarySearchTree<Integer> binarySearchTree = new BinarySearchTree<>();

        binarySearchTree.add(1);
        binarySearchTree.add(5);
        binarySearchTree.add(0);
        binarySearchTree.add(2);
        assertThat(binarySearchTree.getSize(), is(3));
    }

}