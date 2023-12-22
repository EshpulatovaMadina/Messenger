package com.example.messenger.DTO.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DevCreateDto {
    @Size(min = 4, message = "Password should have at least 4 characters")
    private String password;
    @Email(message = "Please provide a valid email address")
    private String email;
}
