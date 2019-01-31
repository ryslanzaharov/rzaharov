package com.example.demo.models;

import lombok.Data;

import javax.persistence.*;

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
