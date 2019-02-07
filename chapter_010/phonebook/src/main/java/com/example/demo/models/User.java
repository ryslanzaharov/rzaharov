package com.example.demo.models;

import lombok.Data;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "users")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "username")
    private String login;

    @Column(name = "password")
    private String password;

    @Column(name = "firstname")
    private String firstname;

    @Column(name = "lastname")
    private String lastname;

    @Column(name = "patronymic")
    private String patronymic;

    @ManyToOne(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    @JoinColumn(name = "roles_id")
    private Role role;

    @Column(name = "enabled")
    private boolean enabled;

    @OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER, mappedBy = "user")
    private List<Contacts> contacts = new ArrayList<>();

    public User() {}

    public User(int id) {
        //  this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id &&
                enabled == user.enabled &&
                Objects.equals(login, user.login) &&
                Objects.equals(password, user.password) &&
                Objects.equals(firstname, user.firstname) &&
                Objects.equals(lastname, user.lastname) &&
                Objects.equals(patronymic, user.patronymic) &&
                Objects.equals(role, user.role) &&
                Objects.equals(contacts, user.contacts);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, login, password, firstname, lastname, patronymic, role, enabled, contacts);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", patronymic='" + patronymic + '\'' +
                ", role=" + role +
                ", enabled=" + enabled +
                ", contacts=" + contacts +
                '}';
    }
}
