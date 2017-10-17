package ru.job4j.iterator.iteratorprimes;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class PrimeIt implements Iterator {

    private int[] numbers;
    private int position = 0;
    private Integer primeNumber;
    private boolean hasN = false;

    public PrimeIt(final int[] numbers) {
        this.numbers = numbers;
    }
    @Override
    public boolean hasNext() {
        if (position >= numbers.length)
            throw new NoSuchElementException();
        primeNumber = numbers[position];
        for (int i = position; i < numbers.length; i++) {
            hasN = true;
            primeNumber = numbers[i];
            if (primeNumber == 1)
                continue;
            primeNumb();
            if (hasN == true) {
                break;
            }
        }
        return position < numbers.length && hasN ? true : false;
    }

    @Override
    public Object next() {
        primeNumber = null;
        for (; position < numbers.length; position++) {
            primeNumber = numbers[position];
            if (primeNumber == 1)
                continue;
            primeNumb();
            if (primeNumber != null && position < numbers.length - 1) {
                position++;
                break;
            }
        }
        if (position > numbers.length || primeNumber == null)
            throw new NoSuchElementException();
        return primeNumber;
    }

    public void primeNumb() {
        for (int n = 2; n < primeNumber - 1; n++) {
            if (primeNumber % n == 0) {
                hasN = false;
                primeNumber = null;
                break;
            }
        }
    }
}
