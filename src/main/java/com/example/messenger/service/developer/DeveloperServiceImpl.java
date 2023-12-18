package com.example.messenger.service.developer;

import com.example.messenger.DTO.request.DevCreateDto;
import com.example.messenger.DTO.response.DevResponseDto;
import com.example.messenger.entity.DeveloperEntity;
import com.example.messenger.exception.DataAlreadyExistsException;
import com.example.messenger.exception.DataNotFoundException;
import com.example.messenger.repository.DevRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DeveloperServiceImpl implements DeveloperService{
    private final DevRepository devRepository;
    private final ModelMapper modelMapper;
    @Override
    public DevResponseDto save(DevCreateDto dto) {
        DeveloperEntity developerEntity = parse(dto);
        Optional<DeveloperEntity> optional = devRepository.findByEmail(dto.getEmail());
        if(optional.isPresent()) {
            throw new DataAlreadyExistsException("Developer is already exists with email: " + dto.getEmail());
        }
        DeveloperEntity save = devRepository.save(developerEntity);
        return parse(save);
    }

    @Override
    public DeveloperEntity findById(UUID developerId) {
        return devRepository.findById(developerId)
                .orElseThrow(()-> new DataNotFoundException("Developer not found with id: "+developerId));
    }

    private DevResponseDto parse(DeveloperEntity save) {
        return new DevResponseDto(
                save.getId(),
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
