package ru.job4j.list.relatedlist;

import java.util.*;

public class RelatedList<E> implements Iterable<E>{

    private Node<E> last;
    private Node<E> first;
    private int size = 0;

    public void add(E el) {
        final Node<E> lt = last;
        final Node<E> newNode = new Node<>(lt, el, null);
        last = newNode;
        if (lt == null)
            first = newNode;
        else
            lt.next = newNode;
        size++;
    }

    public E get(int index) {
        if (index > this.size || index < 0)
            throw new NoSuchElementException();
         Node<E> ft = first;
        for (int i = 0; i < index; i++) {
            ft =ft.next;
        }
        return ft.item;
    }

    public void remove(int index) {
        E el = get(index);
        Node<E> ft = first;
        for (int i = 0; i < index; i++) {
            ft =ft.next;
        }
        Node<E> lt = ft.prev;
        Node<E> nt = ft.next;
        lt.next = nt;
        nt.prev = lt;
        ft.next = null;
        ft.prev = null;
        size--;
    }

    @Override
    public Iterator<E> iterator() {
        return new Itr();
    }

    private static class Node<E> {
        E item;
        Node<E> prev;
        Node<E> next;

        public Node(Node<E> prev, E el, Node<E> next) {
            this.item = el;
            this.next = next;
            this.prev = prev;
        }
    }

    private class Itr implements Iterator<E> {


        private RelatedList.Node<E> lastReturned;
        private RelatedList.Node<E> next;
        private int nextIndex;

        @Override
        public boolean hasNext() {
            return nextIndex <= size - 1;
        }

        @Override
        public E next() {
            if (!hasNext())
                throw new NoSuchElementException();
            if (nextIndex == 0) {
                next = first;
            }
            else
                next = next.next;
            lastReturned = next;
            nextIndex++;
            return lastReturned.item;
        }
    }
}