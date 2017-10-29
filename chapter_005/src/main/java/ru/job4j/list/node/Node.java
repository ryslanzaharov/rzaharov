package ru.job4j.list.node;


import java.util.List;

public class Node<T>{
    private T value;
    Node<T> next;
    private List<Node> list;

    public Node(T value) {
        this.value = value;
    }

    public Node(List<Node> list) {
        this.list = list;

    }

    public boolean hasCycle() {
        boolean result = false;
        int i = 1;
        for (Node node : list) {
            if (i == list.size()) {
                if (node.next == list.get(0))
                    return true;
                else
                    return false;
            }
            if (node.next != list.get(i++)) {
               return false;
            }
        }
        return result;
    }
}
