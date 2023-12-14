package com.example.messenger.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

import java.util.List;

@Entity
public class App extends BaseEntity {
    @ManyToOne
    private Developer developer;
    private String appName;
}
