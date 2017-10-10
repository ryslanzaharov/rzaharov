package ru.job4j.iterator;

import java.util.Iterator;

public class Arrays<T> implements Iterable<T> {

    T [][] values;

    public Arrays(T[][] values) {
        this.values = values;
    }

    @Override
    public Iterator iterator() {
        return new ArrayIterator();
    }

    class ArrayIterator implements Iterator<T> {

            private int index1 = 0;
            private  int index2 = 0;

            @Override
            public boolean hasNext() {

                return index1 < values.length - 1 || index2 < values[index1].length ? true : false;
            }

            @Override
            public T next() {
                if (index2 == values[index1].length) {
                    index1++;
                    index2 = 0;
                }
                return values[index1][index2++];
            }
    }
}
