package ru.rzaharov;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JdbcStorage implements Storage {

    private static final Logger log = LoggerFactory.getLogger(JdbcStorage.class);

    @Override
    public void addUser(User user) {
        System.out.println("store to jdbc");
    }
}
