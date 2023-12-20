package com.example.messenger.DTO.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserResponseDto {
    private UUID id;
    private Long userId;
    private String name;
    private String username;
    private String email;
    private String password;
    private String bio;
    private String photo;
    private UUID appId;
}
