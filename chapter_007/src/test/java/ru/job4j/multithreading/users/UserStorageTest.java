package ru.job4j.multithreading.users;

import org.junit.Test;

import static org.junit.Assert.*;

public class UserStorageTest {

    @Test
    public void whenAddUpdateDeleteAndTransferUsersInTheStorage() {
        UserStorage storage = new UserStorage();

        storage.add(new User(1, 100));
        storage.add(new User(2, 200));

        storage.transfer(1, 2, 50);
        System.out.println(storage.storage);
    }

}