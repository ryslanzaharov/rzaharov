package ru.job4j.Tracker;

public class Item {

    private String id;
    private String name;

    public Item(String name) {
        this.name = name;
    }
    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}
