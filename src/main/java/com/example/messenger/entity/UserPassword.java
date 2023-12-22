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
public class UserPassword extends Code {
    @ManyToOne
    UserEntity user;

    public UserPassword(String code, LocalDateTime sentDate, Integer expiry, UserEntity user) {
        super(code, sentDate, expiry);
        this.user = user;
    }

    public UserPassword(UserEntity user) {
        this.user = user;
    }
}
