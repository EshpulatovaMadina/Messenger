package com.example.messenger.controller;

import com.example.messenger.DTO.request.UserCreateDto;
import com.example.messenger.DTO.request.VerifyDto;
import com.example.messenger.DTO.response.UserResponseDto;
import com.example.messenger.service.user.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.annotation.security.PermitAll;
import jakarta.validation.Valid;
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
    public ResponseEntity<UserResponseDto> signUp(@Valid @RequestBody UserCreateDto userCreateDto, @RequestHeader("X-API-KEY") UUID appId) {
        return ResponseEntity.ok(userService.save(userCreateDto,appId));
    }

    @PermitAll
    @PostMapping("/get-verification-code")
    public String sendVerifyCode(@RequestBody String email) {
        return userService.getVerificationCode(email);
    }

    @Operation(
            description = "This API is used for verifying",
            method = "GET method is supported",
            security = @SecurityRequirement(name = "pre authorize", scopes = {"USER"})
    )
    @PermitAll
    @PostMapping("/verify")
    public UserResponseDto verify(@RequestBody VerifyDto verifyDto) {
        return userService.verify(verifyDto);
    }

}
