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
        Integer evenNumber = eventNumbers();
        return position < numbers.length && evenNumber != null ? true : false;
    }

    @Override
    public Integer next() throws NoSuchElementException{
        Integer evenNumber = eventNumbers();
        if (position >= numbers.length || evenNumber == null)
            throw new NoSuchElementException();
        return numbers[evenNumber];
    }

    public Integer eventNumbers(){
        Integer evenNumber = null;
        for (; position < numbers.length; position++) {
            if (numbers[position] % 2 == 0) {
                evenNumber = position;
                if (position != numbers.length - 1)
                    position++;
                break;
            }
        }
        return evenNumber;
    }
}
