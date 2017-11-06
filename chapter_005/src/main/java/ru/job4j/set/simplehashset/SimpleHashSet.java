package ru.job4j.set.simplehashset;

import java.util.Arrays;

public class SimpleHashSet<E> {

    Integer minValue = Integer.MIN_VALUE;
    Object[] keys;
    private int size;

    public SimpleHashSet(int size) {
        this.keys = new Object[size];
        this.size = size;
        Arrays.fill(keys, minValue);
    }

    public boolean add (E e) {
        Integer i = contains(e);
        if (i != null) {
            keys[i] = e;
            return true;
        }
       return false;
    }

    public int hash(E e) {
        return e.hashCode() % size;
    }

    public Integer contains (E e) {
        for(int i = hash(e); i < size ; i++) {
            if (i == keys.length)
                i = 0;
            if (keys[i].equals(e))
                return null;
            if (keys[i] == minValue) {
                return i;
            }
        }
        return null;
    }
    public boolean remove (E e) {
        for (int i = 0; i < keys.length; i++) {
            if ((Integer)keys[i] == e.hashCode()) {
               keys[i] = minValue;
            }
        }
        return false;
    }
}
