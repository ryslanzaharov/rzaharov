package ru.job4j.multithreading.monitoresynchronizy;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class RelatedListSynchronize<E> implements Iterable<E>{

    private volatile Node<E> last;
    private volatile Node<E> first;

    private int size = 0;

    private final Object lock = new Object();

    public int getSize() {
        synchronized (lock) {
            return size;
        }
    }

    public void add(E el) {
        synchronized (lock) {
            final Node<E> lt;
            lt = last;
            final Node<E> newNode = new Node<>(lt, el, null);
            this.last = newNode;
            if (lt == null)
                first = newNode;
            else
                lt.next = newNode;
            size++;
        }
    }

    public E get(int index) {
        synchronized (lock) {
            if (index > this.size || index < 0)
                throw new NoSuchElementException();
            Node<E> ft = first;
            for (int i = 0; i < index; i++) {
                ft =ft.next;
            }
            return ft.item;
        }
    }

    public E removeFirst() {
        synchronized (lock) {
            Node<E> remove = first;
            Node<E> nextFirst = first.next;
            this.first = nextFirst;
            if (nextFirst == null) {
                return remove.item;
            }
            first.prev = null;
            size--;
            return remove.item;
        }
    }

    public E removeLast() {
        synchronized (lock) {
            Node<E> remove = last;
            try {
                Node<E> prevLast = last.prev;
                this.last = prevLast;
                if (prevLast == null)
                    return remove.item;
                last.next = null;
                size--;
            } catch (NullPointerException npe) {
                throw new NoSuchElementException();
            }
            return remove.item;
        }

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


        private RelatedListSynchronize.Node<E> lastReturned;
        private RelatedListSynchronize.Node<E> next;
        private int nextIndex;

        @Override
        public boolean hasNext() {
            synchronized (lock) {
                return nextIndex <= size - 1;
            }
        }

        @Override
        public E next() {
            synchronized (lock) {
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
}
