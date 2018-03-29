package ru.job4j.sql.xmloptimization;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;


@XmlType(propOrder = { "name","entry" }, name = "entries")
@XmlRootElement
public class Entries {

    private String name;

    private ArrayList<Field> entry;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Field> getEntry() {
        return entry;
    }

    public void setEntry(ArrayList<Field> entry) {
        this.entry = entry;
    }
}
