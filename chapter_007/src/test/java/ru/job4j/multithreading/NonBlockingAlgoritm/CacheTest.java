package ru.job4j.multithreading.NonBlockingAlgoritm;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

public class CacheTest {

    @Test
    public void whenAddUpdateDeleteInCache() {
        Cache cache = new Cache();
        User user1 = new User(1, "Ivan");
        cache.add(user1);
        User user2 = new User(1, "Lida");
        cache.update(user2);
        System.out.println(cache.cache);
        //assertThat(cache.cache.get(user2.getId()).getVersion(), is(1));
    }

}