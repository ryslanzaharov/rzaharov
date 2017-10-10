package ru.job4j.testtask;

import java.util.*;

public class SortingUnits {

    public List<Units> sortAscending(List<Units> list) {

        list.addAll(newUnits(list));
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
        list.addAll(newUnits(list));
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

    public List<Units> newUnits(List<Units> list) {
        ArrayList<String> unitsList = new ArrayList<>();
        ArrayList<Units> result = new ArrayList<>();
        for (Units units : list) {
            unitsList.add(units.getUnitsName());
        }
        TreeSet<String> set = new TreeSet<>();
        for (String str : unitsList) {
            String st = str;
            if (str.contains("\\"))
                st = str.substring(0, str.indexOf('\\'));
            set.add(st);
        }
        for (String seet : set) {
            if (!unitsList.contains(seet))
                result.add(new Units(seet));
        }
        return result;
    }
}
