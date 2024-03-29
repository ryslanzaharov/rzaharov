package ru.job4j.start;

import java.util.*;

public class ConsoleInput implements Input {

    private Scanner scanner = new Scanner(System.in);

    public String ask(String question) {
        System.out.println(question);
        return scanner.nextLine();
    }

    public int ask(String question, ArrayList<Integer> list) {
        int key = Integer.valueOf(this.ask(question));
        boolean exist = false;
        for (int value : list) {
            if (value == key) {
                exist = true;
                break;
            }
        }
        if (exist)
            return key;
        else
            throw new MenuOutException("OUT OF MENU RANGE");
    }
}