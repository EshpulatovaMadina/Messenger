package com.example.messenger.DTO.response;

import com.example.messenger.entity.DeveloperEntity;
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
public class AppResponseDto {
    private UUID id;
    private UUID developerId;
    private String appName;
}
