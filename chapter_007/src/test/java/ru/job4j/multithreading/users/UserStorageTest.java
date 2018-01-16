package ru.job4j.multithreading.users;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.is;

public class UserStorageTest {

    @Test
    public void whenAddUpdateDeleteAndTransferUsersInTheStorage() throws InterruptedException {
        UserStorage storage = new UserStorage();
        User user1 = new User(1, 100);
        User user2 = new User(2, 200);

        storage.add(user1);
        storage.add(user2);
        assertThat(user1.getAmount(), is(100));
        assertThat(user2.getAmount(), is(200));

        new Thread(new Runnable() {
            @Override
            public void run() {
                storage.transfer(1, 2, 10);
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                storage.transfer(1, 2, 20);
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                storage.transfer(2, 1, 80);
            }
        }).start();

        Thread.sleep(1000);
        assertThat(user1.getAmount(), is(150));
        assertThat(user2.getAmount(), is(150));

    }

}