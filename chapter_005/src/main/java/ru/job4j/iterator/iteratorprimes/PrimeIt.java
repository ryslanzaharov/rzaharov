package ru.job4j.iterator.iteratorprimes;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class PrimeIt implements Iterator {

    private int[] numbers;
    private int position = 0;
    private Integer primeNumber;

    public PrimeIt(final int[] numbers) {
        this.numbers = numbers;
    }
    @Override
    public boolean hasNext() {
        return position < numbers.length && primeNumber != null ? true : false;
    }

    @Override
    public Object next() {
        primeNumber = null;
        for (; position < numbers.length; position++) {
            primeNumber = numbers[position];
            for (int i = 2; i < primeNumber - 1; i++) {
                if (primeNumber % i == 0) {
                    primeNumber = null;
                    break;
                }
            }
            if (primeNumber != null) {
                position++;
                break;
            }
        }
        if (position > numbers.length || primeNumber == null)
            throw new NoSuchElementException();
        return primeNumber;
    }
}
