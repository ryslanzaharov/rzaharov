package ru.job4j.set;
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
        boolean ad = true;

        for (Object el : container) {
            if ((T)el == value)
                ad = false;
        }
        if (ad) {
            container[size++] = value;
        }
        if (size == container.length)
            increaseContainer();
    }

    public void sort() {
        deleteNull();
        Arrays.sort(container);
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

    private class Itr implements Iterator {
        private int count = 0;
        @Override
        public boolean hasNext() {
            return count < container.length;
        }

        @Override
        public Object next() {
            if (!hasNext())
                throw new NoSuchElementException();
            return container[count++];
        }
    }
}
