package ru.job4j.multithreading.monitoresynchronizy;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DynamicArraySynchronize<T> implements Iterable<T> {
    private Object[] container;
    private int count = 0;

    private final Object lock = new Object();

    public DynamicArraySynchronize() {
        synchronized (lock) {
            this.container = new Object[2];
        }
    }

    public DynamicArraySynchronize(int capacity) {
        synchronized (lock) {
            this.container = new Object[capacity];
        }
    }

    public void increaseContainer() {
        synchronized (lock) {
            this.container = Arrays.copyOf(container, 2 * container.length);
        }
    }

    public void add(T value) {

        synchronized (lock) {
            if (count == container.length)
                increaseContainer();
            container[this.count++] = value;
        }
    }

    public T get(int index) {
        synchronized (lock) {
            if (index < 0 || index >= container.length)
                throw new ArrayIndexOutOfBoundsException("the element extends beyond the array");
            return (T) container[index];
        }
    }

    public Object[] getAll() {
        synchronized (lock) {
            return (Object[]) container.clone();
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new Iter();
    }

    public class Iter implements Iterator<T> {

        private int index = 0;

        @Override
        public boolean hasNext() {
            synchronized (lock) {
                return this.index < count ? true : false;
            }
        }

        @Override
        public T next() {

            synchronized (lock) {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return (T) container[index++];
            }

        }
    }
}
