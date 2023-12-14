package com.example.messenger.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;

import java.util.List;
import java.util.UUID;

@Entity
public class User extends BaseEntity {
    @Column(unique = true)
    private Long userId;
    private String name;
    @Column(unique = true)
    private String username;
    @Column(unique = true)
    private String email;
    private String password;
    private String bio;
    private String photo;
    @ManyToOne
    private App app;

}
