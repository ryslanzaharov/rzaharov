package ru.job4j.iterator.iteratorprimes;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class PrimeIt implements Iterator<Integer> {

    private int[] array;
    private int index;

    public PrimeIt (int[] array) {
        this.array = array;
    }

    @Override
    public boolean hasNext () {
        boolean hasPrime = false;
        while (index < array.length) {
            if (isPrime(array[index])) {
                hasPrime = true;
                break;
            }
            index++;
        }
        return hasPrime;
    }

    @Override
    public Integer next () {
        if (hasNext()) {
            return array[index++];
        } else {
            throw new NoSuchElementException();
        }
    }

    /**
     * Checks if parameter is prime number or not.
     * @param numberToCheck - number to check
     * @return - true - it is prime number, false - otherwise.
     */
    private boolean isPrime (int numberToCheck) {
        boolean isPrime = true;
        if (numberToCheck == 1) {
            isPrime = false;
        }
        for (int i = 2; i < numberToCheck; i++) {
            if (numberToCheck % i == 0) {
                isPrime = false;
                break;
            }
        }
        return isPrime;
    }
}
