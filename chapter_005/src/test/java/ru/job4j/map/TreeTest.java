package ru.job4j.map;

import com.sun.org.apache.xml.internal.serialize.LineSeparator;
import org.junit.Test;
import ru.job4j.list.node.Node;

import java.util.*;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class TreeTest {

    @Test
    public void whenAddInTheTree() {
        Tree<Integer> tree = new Tree<>();
        tree.add(50, 60);
        tree.add(50, 70);
        tree.add(70, 80);
        tree.add(70, 100);
        tree.add(60, 10);
        assertThat(tree.isBinary(), is(true));
    }

}