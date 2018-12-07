package com.example.urlshortener.models;


import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "url")
@Data
public class Url {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "url")
    private String url;

    @Column(name = "short_url")
    private String short_url;

    @ManyToOne(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    @JoinColumn(name = "users_id")
    private User user;

    public Url() {}

    public Url(String url, String short_url, User user) {
        this.url = url;
        this.short_url = short_url;
        this.user = user;
    }

    @Override
    public String toString() {
        return "Url{" +
                "url='" + url + '\'' +
                ", short_url='" + short_url + '\'' +
                ", user=" + user +
                '}';
    }
}
