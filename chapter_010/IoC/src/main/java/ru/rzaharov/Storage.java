package ru.rzaharov;

import java.util.List;

public interface Storage {

    public void addUser(User user);

    public User getById(int id);

    public void update(int id, User user);

    public List<User> getAll();

    public void remove(User user);
}
