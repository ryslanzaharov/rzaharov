package ru.job4j.array;

/**
 * Класс поворота квадратного массива.
 * @author Ryslan Zaharov (mailto:Ryslan8906137@yandex.ru).
 * @version 01.
 * @since 30.08.17.
 */
public class RotateArray {
    /**
     *Метод поворота квадратного массива.
     */
    public int[][] rotate(int[][] array) {
        int[][] narray = new int[array.length][array[0].length];
        for (int i = 0;i<=array.length-1; i++){
            for (int n = array[i].length-1, a = 0; n >= 0; n-- , a++) {
            narray[i][a] = array[n][i];
            }
        }
        return narray;
    }

}
