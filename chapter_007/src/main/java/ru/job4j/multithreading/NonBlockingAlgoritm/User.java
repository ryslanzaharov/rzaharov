package ru.job4j.multithreading.NonBlockingAlgoritm;

public class User {
    private String name;
    private int id;
    public int version = 0;

    public User(int id, String name) {
        this.name = name;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public int getVersion() {
        return version;
    }

    public void incrementVersion() {
        version++;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", version=" + version +
                '}';
    }
}
