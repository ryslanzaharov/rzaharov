package com.platforms.models;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "engine")
@Data
public class Engine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "type_engine")
    private String type_engine;

    @Column(name = "condition")
    private String conditionEng;

    public Engine() {}

    public Engine(int id, String name, String type_engine, String condition) {
        this.name = name;
        this.type_engine = type_engine;
        this.conditionEng = condition;
        this.id = id;
    }

    public Engine(String name, String type_engine, String condition) {
        this.name = name;
        this.type_engine = type_engine;
        this.conditionEng = condition;
    }

    public Engine(int id) {
        this.id = id;
    }

}
