package ru.job4j.tree;

public class BinarySearchTree<E extends Comparable> {

    private Entry<E> root;
    private int size;

    public BinarySearchTree() {
        root = null;
    }

    private class Entry<E> {
        Entry<E> leftChild;
        Entry<E> rightChild;
        private E value;

        public Entry(E e) {
            this.value = e;
        }
    }

    public boolean add(E e) {
        boolean isAdd = false;
        if (root == null) {
            this.root = new Entry<>(e);
            isAdd = true;
            size++;
        }
        else if (addWithSearch(e, root)){
                isAdd = true;
                size++;
        }
        return isAdd;
    }

    public boolean addWithSearch(E e, Entry<E> current) {
        boolean isAdd = false;
        Entry<E> parent = current;
        if (current.value.compareTo(e) > 0) {
            current = current.leftChild;
            if (current == null) {
                isAdd = true;
                parent.leftChild = new Entry<>(e);
            }
            else
                addWithSearch(e, current);
        }
        else {
            current = current.rightChild;
            if (current == null) {
                isAdd = true;
                parent.rightChild = new Entry<>(e);
            }
            else
                addWithSearch(e, current);
        }
        return isAdd;
    }

    public int getSize() {
        return this.size;
    }
}
