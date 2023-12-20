package com.example.messenger.service.app;

import com.example.messenger.DTO.request.AppCreateDto;
import com.example.messenger.DTO.response.AppResponseDto;
import com.example.messenger.entity.AppEntity;

import java.util.UUID;

public interface AppService {
    AppEntity findById(UUID appId);

    AppResponseDto getById(UUID appId);

    AppResponseDto save(AppCreateDto appCreateDto);
//    AppResponseDto parse (AppEntity appEntity);
}
