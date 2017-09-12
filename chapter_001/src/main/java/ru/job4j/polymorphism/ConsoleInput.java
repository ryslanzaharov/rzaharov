package ru.job4j.polymorphism;

import java.util.Scanner;

public class ConsoleInput {

    private Scanner scanner = new Scanner(System.in);

    public String ask() {
        return scanner.nextLine();
    }
}
