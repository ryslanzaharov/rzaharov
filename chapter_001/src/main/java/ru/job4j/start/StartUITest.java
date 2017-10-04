package ru.job4j.start;

import java.util.ArrayList;

public class StartUITest {

    public static void main(String[] args) {
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("create stub task");
        Input input = new StubInput(arrayList);
        new StartUI(input).init();
    }
}