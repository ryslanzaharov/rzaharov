package ru.job4j.map;

import javax.swing.table.TableRowSorter;
import java.util.*;

class Tree<E extends Comparable<E>> implements SimpleTree<E> {

    private Node<E> node;
    private Node<E> nodeParent;

    private class Node<E> {
         List<Node<E>> children;
        private E value;

        public Node(E value) {
            this.value = value;
        }

        public void addFirstChildren(E children) {
            this.children = new ArrayList<>();
            this.children.add(new Node<>(children));
        }
    }

    @Override
    public boolean add(E parent, E child) {
        boolean isAdd = false;
    if (parent.compareTo(child) != 0)
        if (this.node == null) {
            node = new Node<>(parent);
            nodeParent = node;
            node.addFirstChildren(child);
        }
        else if (searchWithAddition(node, parent, child)) {
            isAdd = true;
        }
        return isAdd;
    }

    public boolean searchWithAddition(Node<E> node, E parent, E child) {
        boolean parNode = false;
        int ind ;
        if (!contains(node, child)) {
        if ( node.value.equals(parent)) {
                node.children.add(new Node<>(child));
        }
        else if (( ind = getIndex(node, parent)) != -1 && node.children.get(ind).children == null) {
                node.children.get(ind).addFirstChildren(child);
            searchWithAddition(node.children.get(ind), parent, child);
        }
        else {
            for (Node n : node.children) {
                if (n.children != null) {
                    searchWithAddition(n, parent, child);
                }
            }
        }
        if (node.equals(nodeParent)) {
            parNode = true;
        }}
        return parNode;
    }

    public boolean contains(Node<E> node, E child) {
        boolean isCont = false;
        for (Node n : node.children) {
            if (n.value.equals(child)) {
                isCont = true;
            }
            if (n.children != null) {
                contains(n, child);
            }
        }
        return isCont;
    }

    public int getIndex(Node<E> node, E value) {
        int index = -1;
        for (int i = 0; i < node.children.size(); i++) {
            if (node.children.get(i).value.equals(value))
                index = i;
        }
        return index;
    }

    public boolean isBinary() {
        boolean binary = true;
        Node<E> bNote = null;
        Queue<Node<E>> queueNote = new ArrayDeque<>();
        if (node != null) {
            queueNote.add(node);
        }
        while(!queueNote.isEmpty()) {
            bNote = queueNote.poll();
            if (bNote.children != null && bNote.children.size() <= 2) {
                queueNote.addAll(bNote.children);
            }
            else {
                binary = false;
                break;
            }
        }

        return binary;
    }
    @Override
    public Iterator<E> iterator() {
        return new Iter();
    }

    private class Iter implements Iterator<E> {

        int index;
        List<E> list;

        @Override
        public boolean hasNext() {
            if (list == null) {
                this.list = new ArrayList<>();
                list.add(node.value);
                getList(node);
            }
            return index < list.size();
        }

        @Override
        public E next() {
            if (!hasNext())
                throw new NoSuchElementException();
            return list.get(index++);
        }

        public void getList(Node<E> node) {
            for (Node n : node.children) {
                if (n.children != null) {
                    list.add((E)n.value);
                   getList(n);
                }
                else
                    list.add((E)n.value);
            }
        }
    }
}
