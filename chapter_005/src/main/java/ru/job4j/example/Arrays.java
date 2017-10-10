package ru.job4j.example;

import java.util.Iterator;
import java.util.NoSuchElementException;


public class Arrays<T> implements Iterable<T> {
    T[] values;  // this contains the actual elements of the array

    // Constructor that takes a "raw" array and stores it
    public Arrays(T[] values) {
        this.values = values;
    }
class ArrayIterator implements Iterator<T> {
    int current = 0;  // the current element we are looking at

    // return whether or not there are more elements in the array that
    // have not been iterated over.
    public boolean hasNext() {
        if (current < Arrays.this.values.length) {
            return true;
        } else {
            return false;
        }
    }

    // return the next element of the iteration and move the current
    // index to the element after that.
    public T next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return values[current++];
    }
}

    public T get(int index) {
        return values[index];
    }

    public void set(int index, T value) {
        values[index] = value;
    }

    public int length() {
        return values.length;
    }

    public Iterator<T> iterator() {
        return new ArrayIterator();
    }

    public static void main(String[] args) {
        String[] strings = new String[]{"Hello", "World"};

        Arrays<String> array = new Arrays<String>(strings);

        System.out.println(array.get(0));

        array.set(1, "Javaland!");

        for (String s : array) {
            System.out.println(s);
        }
    }
}
