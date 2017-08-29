package ru.job4j.array;

public class BubbleSort {

    public int[] sort(int[] array) {
        for (int i = 0; i <= array.length-1; i++) {
            for (int n = 0; n <= array.length-1; n++) {
                if (array[i] < array[n]) {
                    int a = array[n];
                    array[n] = array[i];
                    array[i] = a;
                }
            }
        }
        return array;
    }
}
