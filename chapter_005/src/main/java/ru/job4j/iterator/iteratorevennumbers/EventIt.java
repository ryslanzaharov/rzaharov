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
        boolean hasN = false;
        for (int i = position; i < numbers.length; i++) {
            if (numbers[i] % 2 == 0) {
                hasN = true;
                break;
            }
        }
        return position < numbers.length && hasN ? true : false;
    }

    @Override
    public Integer next() throws NoSuchElementException{
        Integer evenNumber = null;
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
