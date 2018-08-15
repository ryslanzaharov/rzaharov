package ru.rzaharov;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MemoryStorage implements Storage {

    private static final Logger log = LoggerFactory.getLogger(MemoryStorage.class);
    private List<User> users = new ArrayList<>();

    @Override
    public void addUser(User user) {
        users.add(user);
    }

    public User getUserByIndex(int index) {
        return users.get(index);
    }

    public void addUserByIndex(int index, User user) {
        users.add(index, user);
    }

    public void removeUser(int index) {
        users.remove(index);
    }

    public void removeUser(User user) {
        users.remove(user);
    }

    public int getSize() {
        return users.size();
    }
}
