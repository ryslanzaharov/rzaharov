package ru.job4j.Tracker;

import java.util.Date;

public class Item {

    private String id;
    private String name;
    private Date date = new Date();

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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
