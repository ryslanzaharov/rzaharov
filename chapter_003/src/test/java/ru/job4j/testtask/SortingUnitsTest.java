package ru.job4j.testtask;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SortingUnitsTest{

    @Test
    public void whenSortAscending() {
        SortingUnits sortingUnits = new SortingUnits();
        List<Units> list = new ArrayList<>(
                Arrays.asList(
                        new Units("K1"),
                        new Units("K1\\SK1\\SSK1"),
                        new Units("K1\\SK1"),
                        new Units("K1\\SK2"),
                        new Units("K2"),
                        new Units("K2\\SK1\\SSK2"),
                        new Units("K2\\SK1\\SSK1"),
                        new Units("K2\\SK1")
                )
        );
        List<Units> result = sortingUnits.sortAscending(list);
      //  System.out.println(result);
    }

    @Test
    public void whensortingBydescending() {
        SortingUnits sortingUnits = new SortingUnits();
        List<Units> list = new ArrayList<>(
                Arrays.asList(
                        new Units("K1"),
                        new Units("K1\\SK1\\SSK1"),
                        new Units("K1\\SK1"),
                        new Units("K1\\SK2"),
                        new Units("K2"),
                        new Units("K2\\SK1\\SSK2"),
                        new Units("K2\\SK1\\SSK1"),
                        new Units("K2\\SK1")
                )
        );
        List<Units> result = sortingUnits.sortingBydescending(list);
        System.out.println(result);
    }
}
