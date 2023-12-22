package com.example.messenger.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class DevPassword extends Code {
    @ManyToOne
    DeveloperEntity developerEntity;

    public DevPassword(String code, LocalDateTime sentDate, Integer expiry, DeveloperEntity developerEntity) {
        super(code, sentDate, expiry);
        this.developerEntity = developerEntity;
    }

    public DevPassword(DeveloperEntity developerEntity) {
        this.developerEntity = developerEntity;
    }
}
