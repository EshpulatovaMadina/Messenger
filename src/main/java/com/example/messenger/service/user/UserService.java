package com.example.messenger.service.user;

import com.example.messenger.DTO.request.UserCreateDto;
import com.example.messenger.DTO.response.UserResponseDto;

import java.util.UUID;

public interface UserService {
    UserResponseDto save(UserCreateDto userCreateDto, UUID appId);
}
