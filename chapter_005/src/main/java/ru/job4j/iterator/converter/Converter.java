package ru.job4j.iterator.converter;

import java.util.Iterator;
import java.util.function.Consumer;

public class Converter {
    Iterator<Integer> convert(Iterator<Iterator<Integer>> it) {
        return new Iterator<Integer>() {

            Iterator<Integer> iter = it.next();
            @Override
            public boolean hasNext() {
                return it.hasNext() || iter.hasNext() ? true : false;
            }

            @Override
            public Integer next() {
                if (!iter.hasNext()) {
                    iter = it.next();
                }
                return iter.next();
            }
        };
    }
}
