package ru.job4j.list.node;


import java.util.Iterator;
import java.util.List;

public class Node<T>{
    private T value;
    Node<T> next;
    private static int SIZE;

    public Node(T value) {
       this.value = value;
       SIZE++;
    }


    public static class Cycle<T> {

        public static boolean hasCycle(Node first) {
            boolean hasC = false;
            Node prev = first;
            Node ensuing = first.next;
            int sizeC = 1;
            int repeatFirst = 1;
            while(true) {
                if (prev == null || ensuing == null)
                    break;
                if (first.equals(ensuing))
                    repeatFirst++;
                if (first.equals(ensuing) && sizeC == Node.SIZE) {
                    hasC = true;
                    break;
                }
                else {
                    prev = prev.next;
                    ensuing = ensuing.next;
                    sizeC++;
                }
                if (sizeC > Node.SIZE || repeatFirst == Node.SIZE) {
                    hasC = false;
                    break;
                }
            }
            return hasC;
        }
    }
}
