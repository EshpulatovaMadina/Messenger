package com.example.messenger.service.user;

import com.example.messenger.DTO.request.UserCreateDto;
import com.example.messenger.DTO.response.UserResponseDto;
import com.example.messenger.entity.AppEntity;
import com.example.messenger.entity.UserEntity;
import com.example.messenger.repository.UserRepository;
import com.example.messenger.service.app.AppService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final AppService appService;
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;
    @Override
    public UserResponseDto save(UserCreateDto userCreateDto) {
        UserEntity user = parse(userCreateDto);
        userRepository.save(user);
        return parse(user);
    }

    private UserResponseDto parse(UserEntity user) {
        UserResponseDto map = modelMapper.map(user, UserResponseDto.class);
        map.setAppId(user.getAppEntity().getId());
        return map;
    }

    private UserEntity parse(UserCreateDto userCreateDto) {
        AppEntity app = appService.findById(userCreateDto.getAppId());
        UserEntity user = modelMapper.map(userCreateDto, UserEntity.class);
        user.setAppEntity(app);
        return user;
    }
}
