package ru.job4j.list.node;

public class Node<T>{
    private T value;
    Node<T> next;

    public Node(T value) {
       this.value = value;
    }


    public static class Cycle<T> {

        public boolean hasCycle(Node first) {
            Node slow = first;
            Node fast = first;
            if (first == null)
                return false;
            while(slow != null && fast != null && fast.next != null) {
                slow = slow.next;
                fast = fast.next.next;
                if (fast == slow)
                    return true;
            }
            return false;
        }

    }
}
