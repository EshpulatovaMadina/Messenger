package com.example.messenger.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DeveloperEntity extends BaseEntity {
    @Column(unique = true)
    private String email;
    private String password;
//    private Integer appKey;
    @OneToMany
    private List<AppEntity> appEntities;
}
