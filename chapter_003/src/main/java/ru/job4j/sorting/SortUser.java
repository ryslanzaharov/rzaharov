package ru.job4j.sorting;

import java.util.*;

public class SortUser {

    public Set<User> sort (List<User> list) {
            Set<User> userSet = new TreeSet<>();
            userSet.addAll(list);
            return userSet;

    }

    public List<User> sortNameLength (List<User> list) {
            list.sort(
                    new Comparator<User>() {
                        @Override
                        public int compare(User o1, User o2) {
                            return Integer.compare(o1.getName().length(), o2.getName().length());
                        }
                    }
            );
            return list;
    }

    public List<User> sortByAllFields (List<User> list) {
        list.sort(
                new Comparator<User>() {
                    @Override
                    public int compare(User o1, User o2) {
                        int nameCompare = o1.getName().compareTo(o2.getName());
                        return nameCompare == 0 ? Integer.compare(o1.getAge(), o2.getAge()) : nameCompare;
                    }
                }
        );
        return list;
    }
}
