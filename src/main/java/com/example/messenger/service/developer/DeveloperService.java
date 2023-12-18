package com.example.messenger.service.developer;

import com.example.messenger.DTO.request.DevCreateDto;
import com.example.messenger.DTO.response.DevResponseDto;
import com.example.messenger.entity.DeveloperEntity;
import org.springframework.http.RequestEntity;

import java.util.UUID;

public interface DeveloperService {
    DevResponseDto save(DevCreateDto dto);

    DeveloperEntity findById(UUID developerId);
}
