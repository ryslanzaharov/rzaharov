package ru.rzaharov;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    @Override
    public User getById(int id) {
        return users.get(id);
    }

    @Override
    public void update(int id, User user) {
        users.add(id, user);
    }

    @Override
    public List<User> getAll() {
        return users;
    }

    @Override
    public void remove(User user) {
        users.remove(user);
    }

    public int getSize() {
        return users.size();
    }
}
