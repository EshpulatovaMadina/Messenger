package com.example.messenger.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AppEntity extends BaseEntity {
    @ManyToOne
    private DeveloperEntity developerEntity;
    private String appName;
}
