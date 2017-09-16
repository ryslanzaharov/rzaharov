package ru.job4j.polymorphism.task786;

public class Triangle implements Shape {

    public String pic() {
        StringBuilder pic = new StringBuilder();
        pic.append("  +\n");
        pic.append(" + +\n");
        pic.append("+++++");
        return pic.toString();
    }
}
