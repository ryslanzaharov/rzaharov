package ru.job4j.iterator.iteratorevennumbers;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class EventIt implements Iterator<Integer>{

    private int[] numbers;
    private int position = 0;

    public EventIt(final int[] numbers) {
        this.numbers = numbers;
    }

    @Override
    public boolean hasNext() {
        return position < numbers.length && numbers[position] % 2 == 0 ? true : false;
    }

    @Override
    public Integer next() throws NoSuchElementException {
       if (!hasNext())
           throw new NoSuchElementException();
        return numbers[position++];
    }
}
