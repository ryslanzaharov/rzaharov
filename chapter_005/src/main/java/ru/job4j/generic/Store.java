package ru.job4j.generic;

import java.util.List;

public interface Store<T extends Base> {

    T add(T model);

    T update(T model);

    boolean delete(String id);

    T get(int id);

    List getAll();
}
