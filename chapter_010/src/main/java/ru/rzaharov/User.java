package ru.rzaharov;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "users_data")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "firstname")
    private String firstName;

    @Column(name = "lastname")
    private String lastName;

    @Column(name = "year")
    private int year;

    public User() {}

    public User(int id) {
        this.id = id;
    }
}
