package ru.job4j.convert;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
/**
 * Класс для конвертации двумерного массива.
 * @author Ryslan Zaharov (mailto:Ryslan8906137@yandex.ru).
 * @version 01.
 * @since 03.10.17.
 */
public class ConvertList {
    public List<Integer> toList (int[][] array) {
        List<Integer> list = new ArrayList<>();
        for (int ars[] : array) {
            for (int val : ars) {
                list.add(val);
            }
        }
        return list;
    }

    public int[][] toArray (List<Integer> list, int rows) {
        int size = list.size();
        int col = (int)Math.ceil(size /(double) rows);
        int sizeArray = col * rows;
        for (int i =  size; i < sizeArray; i++) {
            list.add(0);
        }

        int[][] array = new int[rows][col];
        Iterator<Integer> iterator = list.iterator();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < col; j++) {
                if (iterator.hasNext())
                array[i][j] = iterator.next();
            }
        }
        return array;
    }
}
