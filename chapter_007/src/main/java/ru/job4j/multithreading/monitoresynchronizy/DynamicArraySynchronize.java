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

    private final Lock locker = new ReentrantLock();

    public DynamicArraySynchronize() {
        this.container = new Object[2];
    }

    public DynamicArraySynchronize(int capacity) {
        this.container = new Object[capacity];
    }

    public void increaseContainer() {
            locker.lock();
        try {
            this.container = Arrays.copyOf(container, 2 * container.length);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            locker.unlock();
        }
    }

    public void add(T value) {
        locker.lock();
        try {
            if (count == container.length) {
                   increaseContainer();
               }
            container[this.count++] = value;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            locker.unlock();
        }
    }

    public T get(int index) {
        locker.lock();
        try {
            if (index < 0 || index >= container.length)
                throw new ArrayIndexOutOfBoundsException("the element extends beyond the array");
            return (T) container[index];
        } finally {
            locker.unlock();
        }
    }

    public Object[] getAll() {
        locker.lock();
        try {
        return container;
        } finally {
            locker.unlock();
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
            locker.lock();
            try {
                return this.index < container.length ? true : false;
            } finally {
                locker.unlock();
            }
        }

        @Override
        public T next() {
            locker.lock();
            try {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return (T) container[index++];
            } finally {
                locker.unlock();
            }
        }
    }
}
