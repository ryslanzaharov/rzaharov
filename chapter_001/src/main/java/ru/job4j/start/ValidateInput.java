package ru.job4j.start;

import java.util.ArrayList;

public class ValidateInput extends ConsoleInput{

    public int ask(String question, ArrayList<Integer> range) {
        boolean invalid = true;
        int value = -1;
        do {
            try {
                value = super.ask(question, range);
                invalid = false;
            }catch(MenuOutException moe) {
                System.out.println("Please select key from menu");
            }catch(NumberFormatException nfe) {
                System.out.println("Please enter validate date again");
            }
        } while (invalid);
        return value;
    }
    }
