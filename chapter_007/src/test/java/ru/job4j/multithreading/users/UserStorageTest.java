package ru.job4j.multithreading.users;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.is;

public class UserStorageTest {

    @Test
    public void whenAddUpdateDeleteAndTransferUsersInTheStorage() {
        UserStorage storage = new UserStorage();
        User user1 = new User(1, 100);
        User user2 = new User(2, 200);

        storage.add(user1);
        storage.add(user2);
        assertThat(user1.getAmount(), is(100));
        assertThat(user2.getAmount(), is(200));

        storage.update(new User(1, 50));
        storage.update(new User(2, 50));
        assertThat(user1.getAmount(), is(150));
        assertThat(user2.getAmount(), is(250));

        storage.transfer(1, 2, 50);
        assertThat(user1.getAmount(), is(100));
        assertThat(user2.getAmount(), is(300));

        storage.delete(user1);
        storage.delete(user2);
        assertThat(storage.getStorage().size(), is(0));

    }

}