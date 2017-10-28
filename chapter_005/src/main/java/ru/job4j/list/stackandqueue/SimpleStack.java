package ru.job4j.list.stackandqueue;

import ru.job4j.list.relatedlist.RelatedList;

public class SimpleStack<T> extends RelatedList<T>{
    public T poll() {
        return (T)removeLast();
    }

    public void push(T value) {
        add(value);
    }
}
