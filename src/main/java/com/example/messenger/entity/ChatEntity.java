package com.example.messenger.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

@Entity
public class ChatEntity extends BaseEntity {
    @OneToOne
    private UserEntity userEntity1;
    @OneToOne
    private UserEntity userEntity2;

    @ManyToOne
    private AppEntity appEntity;
}
