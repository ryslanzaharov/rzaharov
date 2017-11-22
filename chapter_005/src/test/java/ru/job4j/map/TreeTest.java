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
        Tree<String> tree = new Tree<>();
        tree.add("1", "2");
        tree.add("1", "2");
        tree.add("1", "2");
        tree.add("1", "3");
        tree.add("1", "4");
        tree.add("4", "5");
        tree.add("4", "6");
        tree.add("6", "7");
        tree.add("6", "8");
        tree.add("8", "6");
        tree.add("9", "87");
        tree.add("1", "12");
        List<String> expected = new ArrayList<>(
                Arrays.asList(
                        "1", "2", "3", "4", "5", "6", "7", "8", "12"
                )
        );
        Iterator iter = tree.iterator();
        List<String> result = new ArrayList<>();
        while (iter.hasNext()) {
            result.add((String) iter.next());
        }
        assertThat(result, is(expected));
    }

}