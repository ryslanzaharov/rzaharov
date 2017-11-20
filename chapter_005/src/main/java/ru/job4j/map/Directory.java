package ru.job4j.map;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Directory<K,V>  implements Iterable<V>{

    private Node<K,V>[] table;
    int size;

    public Directory() {
        this.table = new Node[5];
    }

    public int indexFor(int h, int length) {
        return Math.abs(h % length);
    }

    public static int hash(int h)
    {
        h ^= (h >>> 20) ^ (h >>> 12);
        return h ^ (h >>> 7) ^ (h >>> 4);
    }

    public boolean insert(K key, V value) {
        boolean isInsert = false;
        if (value != null) {
            if (size == (int) (table.length * 0.75)) {
                transfer(size);
            }
            if (key == null) {
                putForNullKey(value, table);
            }
            else {
                int hash = hash(key.hashCode());
                int index = indexFor(hash, table.length);
                Node<K, V> e = table[index];
                if (e == null) {
                    addNode(hash, key, value, index, table);
                    isInsert = true;
                    size++;
                } else if (!e.key.equals(key)) {
                    index++;
                    for (int i = index; i < table.length; i++) {
                        if (table[i] == null) {
                            addNode(hash, key, value, i, table);
                            isInsert = true;
                            size++;
                            break;
                        }
                    }
                }
            }
        }
        return isInsert;
    }

    public void putForNullKey(V val, Node<K, V> table[]) {
        Node<K, V> nullEl = new Node(0, null, val);
        table[0] = nullEl;
        size++;
    }

    public void transfer(int sizeTable) {
        sizeTable = sizeTable * 2;
        Node<K, V>[] newTable = new Node[sizeTable];

        for (int i = 0; i < table.length; i++) {
            Node<K, V> el = table[i];
            if (el != null)
            if (el.key == null) {
                putForNullKey(el.value, newTable);
            }
            else
                addNode(el.hash, el.key, el.value, indexFor(el.hash, newTable.length), newTable);
        }
        this.table = newTable;

    }

    public void addNode(int hash, K key, V value, int index, Node<K, V> table[])
    {
        table[index] = new Node(hash, key, value);
    }

    public int elementTable(K key) {
        int elemInd = -1;
        int index = indexFor(key.hashCode(), table.length);
        if (containsIndex(index))
            for (int i = 0; i < table.length; i++) {
                if (table[i] != null && table[i].key != null) {
                    if (table[i].key.equals(key))
                        elemInd = index;
                }
            }
        return elemInd;
    }

    public V get(K key) {
        V elem = null;
        if (key == null)
            elem = table[0].value;
        else {
            int ind = elementTable(key);
            if (ind != -1)
                elem = table[ind].value;
        }
        return elem;
    }

    public boolean delete(K key) {
        boolean isDelete = false;
        int ind = elementTable(key);
        if (ind != -1) {
            table[ind] = null;
            size--;
            isDelete = true;
        }
        return isDelete;
    }

    public boolean containsIndex(int index) {
        boolean isContains = false;
        if (index > -1 && index < table.length)
            isContains = true;
        return isContains;
    }

    static class Node<K,V> {

        final int hash;
        final K key;
        V value;

        Node(int hash, K key, V value) {
            this.hash = hash;
            this.key = key;
            this.value = value;
        }
    }

    @Override
    public Iterator<V> iterator() {
        return new Iter();
    }

    class Iter implements Iterator<V>{

        private int size = table.length;
        private int index;

        @Override
        public boolean hasNext() {
            boolean hasN = false;
            while (index < size) {
                if (table[index] != null) {
                    hasN = true;
                    break;
                }
                index++;
            }
            return hasN;
        }

        @Override
        public V next() {
            if (!hasNext())
                throw new NoSuchElementException();
            return (V)table[index++];
        }
    }
}
