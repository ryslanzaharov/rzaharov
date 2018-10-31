package com.example.carplatform.models;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "users")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "create_date")
    private Timestamp created;

    @Column(name = "enabled")
    private boolean enabled;

    @ManyToOne(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    @JoinColumn(name = "roles_id")
    private Role role;

    public User(String login, String password) {
        this.username = login;
        this.password = password;
    }

    public User() {}

    public User(int id) {
      //  this.id = id;
    }

    public User(String username, String password, Timestamp created, boolean enabled, Role role) {
        this.username = username;
        this.password = password;
        this.created = created;
        this.enabled = enabled;
        this.role = role;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + username + '\'' +
                ", password='" + password + '\'' +
                ", created=" + created +
                '}';
    }
}
