package ru.job4j.max;

public class Max {
    public int max(int first, int second){
        return first>second?first:second;
    }

    public int max(int first, int second, int third) {
        first = max(first,second);
        second = max(first,third);
        return second;
    }
}
