package ru.job4j.array;

public class Turn {
    public int[] back(int[] array) {
        for (int i = 0, n = array.length-1; i <= Math.floor((array.length-1)/2); i++, n--) {
            int a = array[n];
            array[n] = array[i];
            array[i] = a;
        }
        return array;
    }
    public static void main(String[] s){
        Turn turn = new Turn();
        int[] array = new int[]{1, 2, 3, 4, 5, 6};
        int[] result = turn.back(array);
        for (int in: array)
        System.out.println(in);
    }
}
