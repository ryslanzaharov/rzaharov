package ru.job4j.set.simplehashset;

import java.util.Arrays;

public class SimpleHashSet<E> {

    Object[] keys;

    public SimpleHashSet(int size) {
        this.keys = new Object[size];
    }

    @Override
    public String toString() {
        return "SimpleHashSet{" +
                "keys=" + Arrays.toString(keys) +
                '}';
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
        return Math.abs(e.hashCode() % keys.length);
    }

    public Integer contains (E e) {
        int it = 0;
        for(int i = hash(e); i < keys.length ; i++) {
            if (keys[i] == null) {
                return i;
            }
            if (keys[i].equals(e))
                return null;
            if (i == keys.length - 1  && it == 0) {
                it = 1;
                i = -1;
                System.out.println("df");
            }
        }
        return null;
    }
    public boolean remove (E e) {
        for (int i = 0; i < keys.length; i++) {
            if (keys[i] != null && (Integer)keys[i] == e.hashCode()) {
               keys[i] = null;
            }
        }
        return false;
    }
}
