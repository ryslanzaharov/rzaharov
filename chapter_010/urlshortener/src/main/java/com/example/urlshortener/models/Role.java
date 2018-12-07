package com.example.urlshortener.models;

import lombok.Data;

import javax.persistence.*;

@Entity(name = "roles")
@Data
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "roles_name")
    private String roles_name;

    public Role(){}

    public Role(String role_name) {
        this.roles_name = roles_name;
    }
}
