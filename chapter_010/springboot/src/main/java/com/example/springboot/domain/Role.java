package com.example.springboot.domain;

import lombok.Data;

import javax.persistence.*;

@Entity(name = "roless")
@Data
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "role")
    private String role;

}
