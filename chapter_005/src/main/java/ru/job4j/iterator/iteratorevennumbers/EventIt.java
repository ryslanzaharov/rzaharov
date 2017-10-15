package ru.job4j.iterator.iteratorevennumbers;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class EventIt implements Iterator<Integer>{

    private int[] numbers;
    private int position = 0;
    private Integer evenNumber;

    public EventIt(final int[] numbers) {
        this.numbers = numbers;
    }

    @Override
    public boolean hasNext() {
        return position < numbers.length && evenNumber != null ? true : false;
    }

    @Override
    public Integer next() throws NoSuchElementException{
        evenNumber = null;
        for (; position < numbers.length; position++) {
            if (numbers[position] % 2 == 0) {
                evenNumber = position;
                position++;
                break;
            }
        }
        if (position > numbers.length || evenNumber == null)
            throw new NoSuchElementException();
        return numbers[evenNumber];
    }
}
