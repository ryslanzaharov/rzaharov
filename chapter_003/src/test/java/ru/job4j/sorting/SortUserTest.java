package ru.job4j.sorting;

import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

public class SortUserTest {
    @Test
    public void whenSortedByAge() {
        SortUser sortUser = new SortUser();
        List<User> list = new ArrayList<>();
        list.addAll(
                Arrays.asList(
                        new User("Petr", 21),
                        new User("Igor", 24),
                        new User("Andrey", 22)
                )
        );
        Set<User> expected = new TreeSet<>(list);
       Set<User> result = sortUser.sort(list);
        assertThat(result, is(expected));
    }
}
