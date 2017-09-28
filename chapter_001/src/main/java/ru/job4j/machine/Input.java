package ru.job4j.machine;

import java.util.Scanner;

public class Input {

    private Scanner scanner = new Scanner(System.in);
    public String ask(String question) {
        System.out.println(question);
        return scanner.nextLine();
    }

}
