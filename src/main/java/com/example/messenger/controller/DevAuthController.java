package com.example.messenger.controller;

import com.example.messenger.DTO.request.DevCreateDto;
import com.example.messenger.DTO.response.DevResponseDto;
import com.example.messenger.service.developer.DeveloperService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dev")
@RequiredArgsConstructor
public class DevAuthController {
    private final DeveloperService developerService;

    @PostMapping("/sign-up")
    public ResponseEntity<DevResponseDto> signUp(@RequestBody DevCreateDto dto) {
        return ResponseEntity.ok(developerService.save(dto));
    }
}
