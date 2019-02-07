package com.example.demo.models;

import lombok.Data;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "phone_book")
@Data
public class Contacts {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "firstname")
    private String firstname;

    @Column(name = "lastname")
    private String lastname;

    @Column(name = "patronymic")
    private String patronymic;

    @Column(name = "telephone_number")
    private String telephone_number;

    @Column(name = "home_phone_number")
    private String home_phone_number;

    @Column(name = "address")
    private String address;

    @Column(name = "e_mail")
    private String e_mail;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "users_id")
    private User user;

    public Contacts() {}

    public Contacts(int id) {}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contacts contacts = (Contacts) o;
        return id == contacts.id &&
                Objects.equals(firstname, contacts.firstname) &&
                Objects.equals(lastname, contacts.lastname) &&
                Objects.equals(patronymic, contacts.patronymic) &&
                Objects.equals(telephone_number, contacts.telephone_number) &&
                Objects.equals(home_phone_number, contacts.home_phone_number) &&
                Objects.equals(address, contacts.address) &&
                Objects.equals(e_mail, contacts.e_mail) &&
                Objects.equals(user, contacts.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstname, lastname, patronymic, telephone_number, home_phone_number, address, e_mail, user);
    }

    @Override
    public String toString() {
        return "Contacts{" +
                "id=" + id +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", patronymic='" + patronymic + '\'' +
                ", telephone_number='" + telephone_number + '\'' +
                ", home_phone_number='" + home_phone_number + '\'' +
                ", address='" + address + '\'' +
                ", e_mail='" + e_mail + '\'' +
                '}';
    }
}
