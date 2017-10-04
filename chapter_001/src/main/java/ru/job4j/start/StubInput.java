package ru.job4j.start;

import java.util.ArrayList;

public class StubInput implements Input {

    private ArrayList<String> answers;
    private int position = 0;

    public StubInput(ArrayList<String> answers) {
        this.answers = answers;
    }
    public String ask(String question) {
        return answers.get(position++);
    }

    public int ask(String queston, ArrayList<Integer> range) {
        return -1;
    }
}