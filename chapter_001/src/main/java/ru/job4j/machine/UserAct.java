package ru.job4j.machine;

public interface UserAct {

    int key();

    void execute(Input input, Money money);

    String info();
}
