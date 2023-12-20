package com.example.messenger.DTO.request;

import com.example.messenger.entity.AppEntity;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserCreateDto {
    private String name;
    private String username;
    private String email;
    private String password;
    private String bio;
}
