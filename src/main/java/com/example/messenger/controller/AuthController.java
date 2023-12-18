package com.example.messenger.controller;

import com.example.messenger.DTO.request.UserCreateDto;
import com.example.messenger.DTO.response.UserResponseDto;
import com.example.messenger.service.user.UserService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.annotation.security.PermitAll;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;
    @PermitAll
    @PostMapping("/sign-up")
    public ResponseEntity<UserResponseDto> register(@RequestBody UserCreateDto userCreateDto) {
        return ResponseEntity.ok(userService.save(userCreateDto));
    }


}
