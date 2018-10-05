package ru.rzaharov.models;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "condition")
@Data
public class Condition {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "condition")
    private String conditionCond;

    @Column(name = "year")
    private int year;

    @Column(name = "mileage")
    private int mileage;

    public Condition(int id, String conditionCond, int year, int mileage) {
        this.conditionCond = conditionCond;
        this.year = year;
        this.mileage = mileage;
        this.id = id;
    }

    public Condition(String conditionCond, int year, int mileage) {
        this.conditionCond = conditionCond;
        this.year = year;
        this.mileage = mileage;
    }

    public Condition() {}

    public Condition(int id) {
        this.id = id;
    }

}
