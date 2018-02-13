package ru.job4j.multithreading.NonBlockingAlgoritm;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Cache {

    public ConcurrentHashMap<Integer, User> cache = new ConcurrentHashMap<>();

    public void add(User user) {
        cache.put(user.getId(), user);
    }

    public void update(User newUser) {
        int vers = newUser.getVersion();
        cache.computeIfPresent(newUser.getId(), ((integer, user1) -> {
            if (vers + 1 == user1.getVersion())
                throw new RuntimeException();
            newUser.incrementVersion();
            return newUser;
        }));
    }

    public User delete(Integer key) {
        return cache.remove(key);
    }

}
