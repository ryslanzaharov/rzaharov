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

    @Test
    public void whenSortedByNameLength() {
        SortUser sortUser = new SortUser();
        List<User> list = new ArrayList<>();
        User user = new User("Andrey", 24);
        User user1 = new User("Roman", 21);
        User user2 = new User("Igor", 22);
        list.addAll(
                Arrays.asList(
                        user, user1, user2
                )
        );
        List<User> result = sortUser.sortNameLength(list);
        List<User> expected = new ArrayList<>();
        expected.addAll(
                Arrays.asList(user2, user1, user)
        );
        assertThat(result, is(expected));
    }

    @Test
    public void whenSortedByNameAndAge() {
        SortUser sortUser = new SortUser();
        User user = new User("Andrey", 24);
        User user1 = new User("Roman", 21);
        User user2 = new User("Igor", 22);
        User user3 = new User("Igor", 23);
        List<User> list = new ArrayList<>(
                Arrays.asList(
                        user, user1, user2, user3)
        );
        List<User> expected = new ArrayList<>(
                Arrays.asList(
                        user, user2, user3, user1
                )
        );
        List<User> result = sortUser.sortByAllFields(list);
        assertThat(result, is(expected));
    }
}
