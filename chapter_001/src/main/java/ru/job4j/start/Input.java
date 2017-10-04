package ru.job4j.start;

import java.util.ArrayList;

public interface Input {

    String ask(String question);

    int ask(String question, ArrayList<Integer> list);

}