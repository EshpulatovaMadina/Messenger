package com.example.messenger.service.user;

import com.example.messenger.DTO.request.UserCreateDto;
import com.example.messenger.DTO.response.UserResponseDto;

public interface UserService {
    UserResponseDto save(UserCreateDto userCreateDto);
}
