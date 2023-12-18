package com.example.messenger.DTO.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class DevResponseDto {
    private UUID id;
    private String email;
    private String password;
    private String appKey;
    private List<AppResponseDto> apps;
}
