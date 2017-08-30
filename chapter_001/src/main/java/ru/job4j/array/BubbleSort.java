package ru.job4j.array;

public class BubbleSort {

    public int[] sort(int[] array) {
        for (int i = array.length-1; i > 0; i--) {
            for (int n = 0; n < i; n++) {
                if (array[n+1] < array[n]) {
                    int m = array[n+1];
                    array[n+1] = array[n];
                    array[n] = m;
                }
            }
        }
        return array;
    }
}
