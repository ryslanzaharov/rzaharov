package ru.job4j.iterator.iteratorprimes;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class PrimeIt implements Iterator {

    private int[] numbers;
    private int position = 0;

    public PrimeIt(final int[] numbers) {
        this.numbers = numbers;
    }
    @Override
    public boolean hasNext() {
        int numb = numbers[position];
        boolean hasN = true;
            for (int i = 2; i < numb - 1; i++) {
                if (numb % i == 0) {
                    System.out.println(numb);
                    hasN = false;
                }
            }
        return position < numbers.length && hasN ? true : false;
    }

    @Override
    public Object next() {
        if (position >= numbers.length)
            throw new NoSuchElementException();
        return numbers[position++];
    }
}
