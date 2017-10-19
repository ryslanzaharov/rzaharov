package ru.job4j.generic;

public class User extends Base {

    private int id;
    private String name;

    private SimpleArray simpleArray;

    public User(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return  "id=" + id +
                ", name='" + name + '\'';
    }
}
