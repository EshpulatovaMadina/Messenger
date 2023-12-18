package com.example.messenger.service.app;

import com.example.messenger.DTO.request.AppCreateDto;
import com.example.messenger.DTO.response.AppResponseDto;
import com.example.messenger.entity.AppEntity;
import com.example.messenger.entity.DeveloperEntity;
import com.example.messenger.exception.DataNotFoundException;
import com.example.messenger.repository.AppRepository;
import com.example.messenger.service.developer.DeveloperService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AppServiceImpl implements AppService{
    private final AppRepository appRepository;
    private final DeveloperService developerService;
    @Override
    public AppEntity findById(UUID appId) {
        return appRepository.findById(appId)
                .orElseThrow(()-> new DataNotFoundException("App not found with id: " + appId));
    }

    @Override
    public AppResponseDto getById(UUID appId) {
        AppEntity appEntity = appRepository.findById(appId)
                .orElseThrow(() -> new DataNotFoundException("App not found with id: " + appId));
        return parse(appEntity);
    }

    @Override
    public AppResponseDto save(AppCreateDto appCreateDto) {
        AppEntity entity = parse(appCreateDto);
        AppEntity appEntity = appRepository.save(entity);
        return parse(appEntity);
    }

    private AppEntity parse(AppCreateDto appCreateDto) {
        DeveloperEntity developerEntity = developerService.findById(appCreateDto.getDeveloperId());
        return new AppEntity(
                developerEntity,
                appCreateDto.getAppName()
        );
    }

    private AppResponseDto parse(AppEntity appEntity) {
        return new AppResponseDto(
                appEntity.getId(),
                appEntity.getId(),
                appEntity.getAppName()
        );
    }
}
