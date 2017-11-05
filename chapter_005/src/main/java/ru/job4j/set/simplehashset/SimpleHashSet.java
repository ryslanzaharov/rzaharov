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
       return contains(e) ? true : false;
    }

    @Override
    public String toString() {
        return "SimpleHashSet{" +
                "keys=" + Arrays.toString(keys) +
                '}';
    }

    public int hash(E e) {
        int x = Math.abs((e.hashCode() >> 10) ^ e.hashCode());
        return e.hashCode() % size;
    }

    public boolean contains (E e) {
        for(int i = hash(e); ; i++) {
            if (i == keys.length)
                i = 0;
            if (keys[i].equals(e))
                return false;
            if (keys[i] == minValue) {
                keys[i] = e;
                return true;
            }
        }
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
