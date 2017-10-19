package ru.job4j.generic;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

public class StoreTest {
    @Test
    public void whenAddingRemoveUpdateAndCompareStorageElements() {
        List<Base> expected = new ArrayList<>(Arrays.asList(new Role(0, "User0"), new User(1, "User1"),
                new Role(2, "User2"), new User(3, "User3"), new User(4, "User4")));

        //when we add to the repository

        Store store = new RoleStore(5);
        store.add(expected.get(0));
        store.add(expected.get(1));
        store.add(expected.get(2));
        store.add(expected.get(3));
        store.add(expected.get(4));
        assertThat(store.getAll(), is(expected));

        //when deleted from the repository

        store.delete("1");
        expected.remove(1);
        assertThat(store.getAll(), is(expected));

        //when we compare the elements of the repository

        assertThat(store.get(3), is(expected.get(3)));

        //when we update the elements of the repository

        Base userUpdate = new User(2, "UserUpdate");
        store.update(userUpdate);
        assertThat(store.get(2), is(userUpdate));
    }


}