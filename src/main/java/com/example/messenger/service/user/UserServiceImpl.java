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

import java.util.Random;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final AppService appService;
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;
    @Override
    public UserResponseDto save(UserCreateDto userCreateDto, UUID appId) {
        UserEntity user = parse(userCreateDto,appId);
        userRepository.save(user);
        return parse(user);
    }

    private UserResponseDto parse(UserEntity user) {
        UserResponseDto map = modelMapper.map(user, UserResponseDto.class);
        map.setAppId(user.getAppEntity().getId());
        return map;
    }

    private UserEntity parse(UserCreateDto userCreateDto, UUID appId) {
        AppEntity app = appService.findById(appId);
        return new UserEntity(
                generate12DigitNumber(),
                userCreateDto.getName(),
                userCreateDto.getUsername(),
                userCreateDto.getEmail(),
                userCreateDto.getPassword(),
                userCreateDto.getBio(),
                null,
                app
        );
    }
    public static Long generate12DigitNumber() {
        Random random = new Random();
        // 10^11 dan 10^12 gacha bo'lgan oraliqdagi tasodifiy son
        return (long) (Math.pow(10, 11) + random.nextInt((int) (Math.pow(10, 12) - Math.pow(10, 11))));
    }
}
