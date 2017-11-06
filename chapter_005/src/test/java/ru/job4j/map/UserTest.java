package ru.job4j.map;

import org.junit.Test;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

public class UserTest {

    @Test
    public void whenAdd() {
        Map<User, Object> map = new HashMap<>();
        Calendar calendar = new GregorianCalendar(1996, 11, 4);
        map.put(new User("Egor", 2, calendar), new Object());
        map.put(new User("Egor", 2, calendar), new Object());
        System.out.println(map);
    }

}