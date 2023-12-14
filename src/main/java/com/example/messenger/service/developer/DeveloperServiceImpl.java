package com.example.messenger.service.developer;

import com.example.messenger.DTO.request.DevCreateDto;
import com.example.messenger.DTO.response.DevResponseDto;
import com.example.messenger.entity.DeveloperEntity;
import com.example.messenger.repository.DevRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeveloperServiceImpl implements DeveloperService{
    private final DevRepository devRepository;
    private final ModelMapper modelMapper;
    @Override
    public DevResponseDto save(DevCreateDto dto) {
        DeveloperEntity developerEntity = parse(dto);
        DeveloperEntity save = devRepository.save(developerEntity);
        return parse(save);
    }

    private DevResponseDto parse(DeveloperEntity save) {
        return new DevResponseDto(
                save.getEmail(),
                save.getPassword(),
                save.getAppKey(),
                null
        );
    }

    private DeveloperEntity parse(DevCreateDto dto) {
        return modelMapper.map(dto, DeveloperEntity.class);
    }
}
