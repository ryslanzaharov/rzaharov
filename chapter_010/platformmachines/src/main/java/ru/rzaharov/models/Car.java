package ru.rzaharov.models;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "car") @Data
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "mark")
    private String mark;

    @Column(name = "model")
    private String model;

    @Column(name = "body_type")
    private String body_type;

    @Column(name = "price")
    private Integer price;

    @Column(name = "sale")
    private String sale;

    @ManyToOne(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    @JoinColumn(name = "engine_id")
    private Engine engine;

    @ManyToOne(cascade = {CascadeType.MERGE}, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    @JoinColumn(name = "condition_id")
    private Condition condition;

    @Column(name = "photo")
    private String photo;

    @Column(name = "dates")
    private Timestamp date;

    public Car() {}

    public Car(int id) {
        this.id = id;
    }

}
