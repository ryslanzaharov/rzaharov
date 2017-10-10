package ru.job4j.testtask;

import org.junit.Test;
import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SortingUnitsTest{

    @Test
    public void whenSortAscending() {
        SortingUnits sortingUnits = new SortingUnits();
        Units units = new Units("K1");
        Units units1 = new Units("K1\\SK1\\SSK1");
        Units units2 = new Units("K1\\SK1");
        Units units3 = new Units("K1\\SK2");
        Units units4 = new Units("K2");
        Units units5 = new Units("K2\\SK1\\SSK2");
        Units units6 = new Units("K2\\SK1\\SSK1");
        Units units7 = new Units("K2\\SK1");
        Units units8 = new Units("K1\\SK1\\SSK2");
        List<Units> list = new ArrayList<>(
                Arrays.asList(
                         units1, units2 ,units3, units4, units5, units6, units7, units8
                )
        );
        List<Units> result = sortingUnits.sortAscending(list);
        List<Units> expected = new ArrayList<>(
                Arrays.asList(
                        units, units2, units1, units8, units3, units4, units7, units6, units5
                )
        );
        assertThat(result.toString(), is(expected.toString()));
    }

    @Test
    public void whensortingBydescending() {
        SortingUnits sortingUnits = new SortingUnits();
        Units units = new Units("K1");
        Units units1 = new Units("K1\\SK1\\SSK1");
        Units units2 = new Units("K1\\SK1");
        Units units3 = new Units("K1\\SK2");
        Units units4 = new Units("K2");
        Units units5 = new Units("K2\\SK1\\SSK2");
        Units units6 = new Units("K2\\SK1\\SSK1");
        Units units7 = new Units("K2\\SK1");
        Units units8 = new Units("K1\\SK1\\SSK2");
        List<Units> list = new ArrayList<>(
                Arrays.asList(
                        units, units1, units2 ,units3, units5, units6, units7, units8
                )
        );
        List<Units> result = sortingUnits.sortingBydescending(list);
        List<Units> expected = new ArrayList<>(
                Arrays.asList(
                        units5, units6, units7, units4, units3, units8, units1, units2, units

                )
        );
        assertThat(result.toString(), is(expected.toString()));
    }
}
