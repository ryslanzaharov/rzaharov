package ru.job4j.machine;

public abstract class BaseAct implements UserAct {

    private int key;
    private String name;

    public BaseAct(String name, int key) {
        this.key = key;
        this.name = name;
    }

    public String info() {
        return String.format("%s. %s", this.key() , this.name);
    }
}
