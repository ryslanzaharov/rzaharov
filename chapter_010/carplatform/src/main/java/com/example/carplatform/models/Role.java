package com.example.carplatform.models;

import lombok.Data;

import javax.persistence.*;

@Entity(name = "roles")
@Data
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "role_name")
    private String role_name;

    public Role(){}

    public Role(String role_name) {
        this.role_name = role_name;
    }
}
