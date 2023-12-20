package com.example.messenger.controller;

import com.example.messenger.DTO.request.UserCreateDto;
import com.example.messenger.DTO.response.UserResponseDto;
import com.example.messenger.service.user.UserService;
import jakarta.annotation.security.PermitAll;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;
    @PermitAll
    @PostMapping("/sign-up")
    public ResponseEntity<UserResponseDto> signUp(@RequestBody UserCreateDto userCreateDto, @RequestHeader("X-API-KEY") UUID appId) {
        return ResponseEntity.ok(userService.save(userCreateDto,appId));
    }


}
