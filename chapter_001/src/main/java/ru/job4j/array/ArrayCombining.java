package ru.job4j.array;

public class ArrayCombining {
    public int[] combination(int[] mas1, int[] mas2) {
        int[] union = new int[mas1.length + mas2.length];
        for (int i = 0, n = 0, j = 0; i < union.length  ; i++) {

            if (n == mas1.length) {
                System.arraycopy(mas2, j, union, i, mas2.length - j);
                break;
            }
            else if (j == mas2.length) {
                System.arraycopy(mas1, n, union, i, mas1.length - n);
                break;
            }
            else if (mas1[n] == mas2[j]) {
                union[i++] = mas1[n];
                union[i] = mas2[j];
                n++;j++;

            }
            else if (mas1[n] > mas2[j]) {
                union[i] = mas2[j];
                j++;
            }
            else {
                union[i] = mas1[n];
                n++;
            }
        }
        return union;
    }
}