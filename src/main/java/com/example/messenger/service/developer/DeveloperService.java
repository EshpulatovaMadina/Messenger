package com.example.messenger.service.developer;

import com.example.messenger.DTO.request.DevCreateDto;
import com.example.messenger.DTO.response.DevResponseDto;
import org.springframework.http.RequestEntity;

public interface DeveloperService {
    DevResponseDto save(DevCreateDto dto);
}
