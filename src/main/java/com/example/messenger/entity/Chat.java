package com.example.messenger.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class Chat extends BaseEntity {
    @OneToOne
    private User user1;
    @OneToOne
    private User user2;

    @ManyToOne
    private App app;
}
