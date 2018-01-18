package ru.job4j.multithreading.monitoresynchronizy;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

@ThreadSafe
public class DynamicArraySynchronize<T> implements Iterable<T> {
    @GuardedBy("container")
    private Object[] container;
    private int count = 0;

    public DynamicArraySynchronize() {
        this.container = new Object[2];
    }

    public DynamicArraySynchronize(int capacity) {
        this.container = new Object[capacity];
    }

    public void increaseContainer() {
        synchronized (this.container) {
            this.container = Arrays.copyOf(container, 2 * container.length);
        }
    }

    public void add(T value) {

        synchronized (this.container) {
            if (count == container.length) {
                increaseContainer();
            }
        }
        container[this.count++] = value;
    }

    public T get(int index) {
        synchronized(this.container) {
            if (index < 0 || index >= container.length)
                throw new ArrayIndexOutOfBoundsException("the element extends beyond the array");
            return (T) container[index];
        }
    }

    public Object[] getAll() {
        synchronized (this.container) {
            return container;
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
            synchronized (container) {
                return this.index < container.length ? true : false;
            }
        }

        @Override
        public T next() {
            synchronized (container) {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return (T) container[index++];
            }
        }
    }
}
