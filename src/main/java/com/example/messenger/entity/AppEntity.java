package com.example.messenger.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;

@Entity
public class AppEntity extends BaseEntity {
    @ManyToOne
    private DeveloperEntity developerEntity;
    private String appName;
}
