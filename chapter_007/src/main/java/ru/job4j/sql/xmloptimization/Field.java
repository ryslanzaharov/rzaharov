package ru.job4j.sql.xmloptimization;

import javax.xml.bind.annotation.XmlType;

@XmlType(propOrder = {  }, name = "field")
public class Field {

    private String field;

//    public Field(String field) {
//        this.field = field;
//    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }
}
