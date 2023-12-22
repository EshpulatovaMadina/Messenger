package com.example.messenger.service.user;

import com.example.messenger.DTO.request.EditedProfileDto;
import com.example.messenger.DTO.request.UserCreateDto;
import com.example.messenger.DTO.request.VerifyDto;
import com.example.messenger.DTO.response.UserResponseDto;
import com.example.messenger.entity.UserEntity;

import java.util.UUID;

public interface UserService {
    UserResponseDto save(UserCreateDto userCreateDto, UUID appId);
    void emailSend(UserEntity userEntity);
    UserResponseDto editProfile(EditedProfileDto editedProfileDto, UUID appId);

    String getVerificationCode(String email);

    UserResponseDto verify(VerifyDto verifyDto);
}
