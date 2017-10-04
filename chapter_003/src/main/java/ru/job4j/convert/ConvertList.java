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
        Iterator<Integer> iteratorNull = list.iterator();
        while(iteratorNull.hasNext()) {
            if ((iteratorNull.next())==(null))
                iteratorNull.remove();
        }
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

    public List<Integer> convert (List<int[]> list) {
        List<Integer> listConvert = new ArrayList<>();
        for (int[] array : list) {
            for (int value : array) {
                listConvert.add(value);
            }
        }
        return listConvert;
    }
}
