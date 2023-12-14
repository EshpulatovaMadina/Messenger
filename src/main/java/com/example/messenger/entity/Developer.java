package com.example.messenger.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

import java.util.List;

@Entity
public class Developer extends BaseEntity {
    @Column(unique = true)
    private String email;
    private String password;
    private String appKey;
    @OneToMany
    private List<App> apps;
}
