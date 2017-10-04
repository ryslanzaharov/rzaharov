package ru.job4j.start;

import java.util.ArrayList;
import java.util.Arrays;

public class StartUI {

    private ArrayList<Integer> ranges = new ArrayList<>();
    private Input input;

    public StartUI(Input input) {
        this.input = input;
    }
    public void init() {
        Tracker tracker = new Tracker();
        ranges.addAll(Arrays.asList(0, 1, 2, 3, 4, 5, 6));
        MenuTracker menu = new MenuTracker(this.input, tracker);
        menu.fillActions();
        do {
            menu.show();
            menu.select(input.ask("Select:", ranges));
            System.out.println("____________________");
        }while(true);
    }

    public static void main(String[] args) {
        Input input = new ValidateInput();
        new StartUI(input).init();
    }
}