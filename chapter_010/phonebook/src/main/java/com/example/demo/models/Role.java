package com.example.demo.models;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "roles")
@Data
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "roles_name")
    private String roles_name;

    public Role() {}

    public Role(int id) {
        this.id = id;
    }

    public Role(String roles_name) {
        this.roles_name = roles_name;
    }
}
