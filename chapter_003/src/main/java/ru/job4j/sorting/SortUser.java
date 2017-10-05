package ru.job4j.sorting;

import java.util.*;

public class SortUser {

    public Set<User> sort (List<User> list) {
            Set<User> userSet = new TreeSet<>();
            userSet.addAll(list);
            return userSet;

    }
}
