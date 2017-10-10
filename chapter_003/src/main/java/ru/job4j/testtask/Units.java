package ru.job4j.testtask;

public class Units implements Comparable<Units>{

    private String unitsName;


    public Units(String unitsName) {
        this.unitsName = unitsName;
    }

    public String getUnitsName() {
        return unitsName;
    }

    @Override
    public String toString() {
        return "Units{" +
                "unitsName='" + unitsName + '\'' +
                '}';
    }

    public int compareTo(Units o) {
        return this.unitsName.compareTo(o.unitsName);
    }
}
