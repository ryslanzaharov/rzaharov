package ru.job4j.list;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class DynamicArray<T> implements Iterable<T> {

    private Object[] container;
    private int capacity = 2;
    private int count = 0;

    public DynamicArray() {
        this.container = new Object[this.capacity];
    }

    public DynamicArray(int capacity) {

        this.container = new Object[capacity];
    }

    public void countingElements() {
        this.capacity = 2 * this.capacity;
        Object[] newContainer = new Object[capacity];
        System.arraycopy(container, 0, newContainer, 0, container.length);
        this.container = newContainer;
    }

    public void add(T value) {
        if (count == container.length) {
            countingElements();
        }
        container[this.count++] = value;
    }

    public T get(int index) {
        return (T)container[index];
    }

    public T getAll() {
        return (T)container;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iter();
    }

    public class Iter implements Iterator<T> {

        private int index = 0;

        @Override
        public boolean hasNext() {
            return this.index < container.length ? true : false;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return (T)container[index++];
        }
    }
}
