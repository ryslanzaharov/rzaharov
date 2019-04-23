package com.example.urlshortener.models;


import lombok.Data;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;

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

    @Column(name = "enabled")
    private boolean enabled;

//    @Column(name = "roles_id")
//    private Integer roles_id;
//    @ManyToOne(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
//    @JoinColumn(name = "roles_id")
//    private Role role;

    @Column(name = "roles_id")
    private Integer role;

    public User() {
    }

    public User(int id) {
        //  this.id = id;
    }

    public User(String username, String password, boolean enabled, Integer role) {
        this.username = username;
        this.password = password;
        this.enabled = enabled;
        this.role = role;
        //   this.role = role;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", enabled=" + enabled +
                '}';
    }
}
