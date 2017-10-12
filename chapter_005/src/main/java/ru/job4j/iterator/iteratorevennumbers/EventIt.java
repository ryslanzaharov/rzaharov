package ru.job4j.iterator.iteratorevennumbers;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class EventIt implements Iterator<Integer>{

    private Integer[] numbers;
    private int position = 0;

    public EventIt(final int[] numbers) {
        ArrayList<Integer> list = new ArrayList<>();
        for (int numb : numbers) {
            if (numb %2 == 0)
                list.add(numb);
        }
        this.numbers = list.toArray(new Integer[list.size()]);
    }

    @Override
    public boolean hasNext() {
        return position < numbers.length;
    }

    @Override
    public Integer next() throws NoSuchElementException {
        Integer numb = null;
        try {
            numb = numbers[position++];
        }catch (ArrayIndexOutOfBoundsException nsee) {
            throw new NoSuchElementException();
        }
        return numb;
    }
}
