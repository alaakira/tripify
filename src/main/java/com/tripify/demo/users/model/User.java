package com.tripify.demo.users.model;

import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class User {

    public User() {
    }

    public User(long id, String phone, String password) {
        this.id = id;
        this.phone = phone;
        this.password = password;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @Column(name = "phone")
    private String phone;

    @Column(name = "password")
    private String password;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
