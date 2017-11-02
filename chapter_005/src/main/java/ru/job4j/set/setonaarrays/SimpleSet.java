package ru.job4j.set.setonaarrays;
import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleSet<T> implements Iterable<T>, Comparable<T> {

    private Object[] container;
    private int size =0;

    public SimpleSet() {
        container = new Object[3];
    }

    public SimpleSet(int capacity) {
        container = new Object[capacity];
    }

    public void add(T value) {
        ensureCapacity();
        if (!contains(value)) {
            container[size++] = value;
        }
    }

    public boolean contains(T value) {
        for (Object el : container) {
            if (el != null && ((T)el).equals(value))
                return true;
        }
        return false;
    }

    public void sort() {
        deleteNull();
        Arrays.sort(container);
    }

    public void ensureCapacity() {
        if (size == container.length)
            increaseContainer();
    }

    public void increaseContainer() {
        this.container = Arrays.copyOf(container, container.length * 2);
    }

    public void deleteNull() {
        int count = 0;
        for (Object ob : container) {
            if (ob == null)
                count++;
        }
        this.container = Arrays.copyOf(container, container.length - count);
    }

    @Override
    public Iterator<T> iterator() {
        return new Itr();
    }

    @Override
    public int compareTo(T o) {

        return this.compareTo(o);
    }

    private class Itr<T> implements Iterator<T> {
        private int count = 0;
        @Override
        public boolean hasNext() {

            return count < size;
        }

        @Override
        public T next() {
            if (!hasNext())
                throw new NoSuchElementException();
            return (T)container[count++];
        }
    }
}
