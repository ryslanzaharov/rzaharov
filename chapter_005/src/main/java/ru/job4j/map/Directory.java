package ru.job4j.map;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Directory<K,V>  implements Iterable<V>{

    private Object[] directory;

    public Directory(int size) {
        this.directory = new Object[size];
    }

    public int hash(K key) {
        return Math.abs(key.hashCode() % directory.length);
    }

    public boolean insert(K key, V value) {
        int index = hash(key);
        if (index > -1 && index < directory.length) {
            if (directory[index] == null) {
                directory[index] = value;
                return true;
            }
            else
                return false;
        }
        else
            return false;
    }
    public V get(K key) {
        int index = hash(key);
        if (index > -1 && index < directory.length)
        return (V) directory[index];
        else
            return null;
    }
    public boolean delete(K key) {
        int index = hash(key);
        if (index > -1 && index < directory.length) {
            directory[index] = null;
            return true;
        }
        else
            return false;
    }

    @Override
    public Iterator<V> iterator() {
        return new Iter();
    }

    class Iter implements Iterator<V>{

        private int size = directory.length;
        private int index;

        @Override
        public boolean hasNext() {
            boolean hasN = false;
            while (index < size) {
                if (directory[index] != null) {
                    hasN = true;
                    break;
                }
                index++;
            }
            return hasN;
        }

        @Override
        public V next() {
            if (hasNext())
                return (V)directory[index++];
            else
                throw new NoSuchElementException();
        }
    }
}
