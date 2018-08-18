package ru.rzaharov;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class UserStorage {

    private static final Logger log = LoggerFactory.getLogger(UserStorage.class);

    private final Storage storage;


    public UserStorage(final Storage storage) {
        this.storage = storage;
    }

    public void add(User user) {
        this.storage.addUser(user);
    }
}
