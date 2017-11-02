package ru.job4j.set.setonalinkedlist;
import java.util.Iterator;
import java.util.NoSuchElementException;


public class SimpleSet<E> implements Iterable<E>{

    private Node<E> last;
    private Node<E> first;
    private int size = 0;

    public void add(E el) {
        final Node<E> lt = last;
        if (!contains(el)) {
            final Node<E> newNode = new Node<>(lt, el, null);
            this.last = newNode;
            if (lt == null)
                first = newNode;
            else
                lt.next = newNode;
            size++;
        }
    }

    public boolean contains(E el) {
        Iterator it = this.iterator();
        while (it.hasNext()) {
            if (it.next().equals(el)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iter();
    }

    private static class Node<E>{
        Node<E> next;
        Node<E> prev;
        E item;
        int count = 0;

        public Node(Node<E> prev, E item, Node<E> next) {
            this.prev = prev;
            this.item = item;
            this.next = next;
        }

    }

    public class Iter implements Iterator<E> {

        private SimpleSet.Node<E> next;
        private int nextIndex;

        @Override
        public boolean hasNext() {
            return nextIndex < size;
        }

        @Override
        public E next() {
            if (!hasNext())
                throw new NoSuchElementException();
           if (nextIndex == 0)
               next = first;
           else
               next = next.next;
           nextIndex++;
           return next.item;
        }
    }
}
