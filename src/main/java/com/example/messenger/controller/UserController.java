package com.example.messenger.controller;

import com.example.messenger.DTO.request.EditedProfileDto;
import com.example.messenger.DTO.response.UserResponseDto;
import com.example.messenger.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    @PutMapping("/edit-profile")
    public ResponseEntity<UserResponseDto> editProfile(@RequestBody EditedProfileDto editedProfileDto, @RequestHeader("X-API-KEY") UUID appId) {
        return ResponseEntity.ok(userService.editProfile(editedProfileDto,appId));
    }
}
