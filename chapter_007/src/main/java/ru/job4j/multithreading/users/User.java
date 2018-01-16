package ru.job4j.multithreading.users;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

public class User {

    private int id;
    private int amount;

    public User(int id, int amount) {
        this.id = id;
        this.amount = amount;
    }

    public int getId() {
        return id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

}
