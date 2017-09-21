package ru.job4j.start;

public abstract class BaseAction implements UserAction {

    private String name;
    private int key;
    public BaseAction(String name, int key) {
        this.name = name;
        this.key = key;
    }

    public String info() {
        return String.format("%s. %s", this.key(), this.name);
    }
}
