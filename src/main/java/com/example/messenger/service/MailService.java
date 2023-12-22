package com.example.messenger.service;

import com.example.messenger.DTO.request.MailDto;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MailService {
    private final JavaMailSender mailSender;
    public void sendMail(MailDto dto) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(dto.getEmail());
        simpleMailMessage.setText(dto.getMessage());
        mailSender.send(simpleMailMessage);
    }

}
