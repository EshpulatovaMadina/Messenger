package com.example.messenger.controller;

import com.example.messenger.DTO.request.AppCreateDto;
import com.example.messenger.DTO.response.AppResponseDto;
import com.example.messenger.service.app.AppService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/app")
@RequiredArgsConstructor
public class AppController {
    private final AppService appService;
    @PostMapping("/create")
    public ResponseEntity<AppResponseDto> create(@RequestBody AppCreateDto appCreateDto) {
        return ResponseEntity.ok(appService.save(appCreateDto));
    }
}
