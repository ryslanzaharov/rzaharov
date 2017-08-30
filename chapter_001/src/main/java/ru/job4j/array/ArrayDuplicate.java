package ru.job4j.array;

import java.util.Arrays;

public class ArrayDuplicate {
    public String[] remove(String[] array) {
        int kol = 0;
        for (int i = 0; i <= array.length-1; i++) {
            for (int n = 0;n <= array.length-1; n++) {
                if (i != n && n != array.length-1 && i < n) {
                    if (array[i].equals(array[n])) {
                        String str = array[n];
                        array[n] = array[n+1];
                        array[n+1] = str;
                        kol++;
                    }
                }
            }
        }
        for (int i = 0; i <= array.length-1; i++) {
        if (array[0].equals(array[i]))
            kol = i;
        }
        return Arrays.copyOf(array, kol);
    }

}
