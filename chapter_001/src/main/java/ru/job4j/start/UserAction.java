package ru.job4j.start;

public interface UserAction {

    int key();

    void execute(Input input, Tracker tracker);

    String info();
}