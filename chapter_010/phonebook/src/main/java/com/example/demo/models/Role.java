package com.example.demo.models;

import lombok.Data;

import javax.persistence.*;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Role role = (Role) o;
        return id == role.id &&
                Objects.equals(roles_name, role.roles_name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, roles_name);
    }
}
