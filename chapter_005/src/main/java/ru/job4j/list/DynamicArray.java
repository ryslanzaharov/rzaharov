package ru.job4j.list;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class DynamicArray<T> implements Iterable<T> {

    private Object[] container;
    private int count = 0;

    public DynamicArray() {
        this.container = new Object[2];
    }

    public DynamicArray(int capacity) {

        this.container = new Object[capacity];
    }

    public void arrayCopy() {
       this.container = Arrays.copyOf(container, 2 * container.length);
    }

    public void add(T value) {
        if (count == container.length) {
            arrayCopy();
        }
        container[this.count++] = value;
    }

    public T get(int index) {
        if (index < 0 || index >= container.length )
            throw new ArrayIndexOutOfBoundsException("the element extends beyond the array");
        return (T)container[index];
    }

    public Object[] getAll() {
        return container;
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
