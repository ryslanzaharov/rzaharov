package ru.job4j.listinmap;

public class User {
    private int id;
    private String name;
    private String city;

    public User(String name, String  city) {
        this.name = name;
        this.city = city;
    }

    public User(int id, String name, String  city) {
        this.id = id;
        this.name = name;
        this.city = city;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCity() {
        return city;
    }
}
