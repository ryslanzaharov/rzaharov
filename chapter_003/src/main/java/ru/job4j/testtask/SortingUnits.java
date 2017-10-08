package ru.job4j.testtask;

import java.util.Comparator;
import java.util.List;

public class SortingUnits {

    public List<Units> sortAscending(List<Units> list) {
        list.sort(
                new Comparator<Units>() {
                    @Override
                    public int compare(Units o1, Units o2) {
                        return o1.getUnitsName().compareTo(o2.getUnitsName());
                    }
                }
        );
        return list;
    }


    public List<Units> sortingBydescending(List<Units> list) {

        list.sort(
                new Comparator<Units>() {
                    @Override
                    public int compare(Units o1, Units o2) {
                        return o2.getUnitsName().compareTo(o1.getUnitsName());
                    }
                }
        );
        return list;
    }
}
