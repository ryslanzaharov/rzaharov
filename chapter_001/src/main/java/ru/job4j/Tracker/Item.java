package ru.job4j.Tracker;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;

/**
 * Класс описывающий заявку.
 * @author Ryslan Zaharov (mailto:Ryslan8906137@yandex.ru).
 * @version 01.
 * @since 31.03.18.
 */

public class Item {

    private String id;
    private String name;
//    private Date date = new Date();
    private Timestamp date = new Timestamp(System.currentTimeMillis());

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

    public Timestamp getDate() {
        return date;
    }
}
