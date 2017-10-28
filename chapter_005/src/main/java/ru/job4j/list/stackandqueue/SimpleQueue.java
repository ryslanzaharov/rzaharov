package ru.job4j.list.stackandqueue;

import ru.job4j.list.relatedlist.RelatedList;

public class SimpleQueue<T> extends RelatedList {
    public T poll() {
        return (T)removeFirst();
    }

    public void push(T value) {
        add(value);
    }
}
