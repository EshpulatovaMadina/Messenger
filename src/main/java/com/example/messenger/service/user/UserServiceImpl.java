package com.example.messenger.service.user;

import com.example.messenger.DTO.request.EditedProfileDto;
import com.example.messenger.DTO.request.MailDto;
import com.example.messenger.DTO.request.UserCreateDto;
import com.example.messenger.DTO.request.VerifyDto;
import com.example.messenger.DTO.response.UserResponseDto;
import com.example.messenger.entity.AppEntity;
import com.example.messenger.entity.UserEntity;
import com.example.messenger.entity.Code;
import com.example.messenger.entity.UserPassword;
import com.example.messenger.exception.BadRequestException;
import com.example.messenger.exception.DataNotFoundException;
import com.example.messenger.repository.UserPasswordRepository;
import com.example.messenger.repository.UserRepository;
import com.example.messenger.service.MailService;
import com.example.messenger.service.app.AppService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final AppService appService;
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final MailService mailService;
    private final UserPasswordRepository userPasswordRepository;
    @Override
    public UserResponseDto save(UserCreateDto userCreateDto, UUID appId) {
        UserEntity user = parse(userCreateDto,appId);
        user.setPassword(passwordEncoder.encode(userCreateDto.getPassword()));
        userRepository.save(user);
        emailSend(user);
        return parse(user);
    }

    @Override
    public void emailSend(UserEntity userEntity) {
        String generatedString = RandomStringUtils.randomAlphanumeric(5);
        System.err.println("generatedString = " + generatedString);
        MailDto mailDto = new MailDto(generatedString, userEntity.getEmail());
        mailService.sendMail(mailDto);
        userPasswordRepository.save(new UserPassword(generatedString, LocalDateTime.now(),3,userEntity));
    }

    @Override
    public String getVerificationCode(String email) {
        UserEntity user = userRepository.findByEmail(email)
                .orElseThrow(() -> new DataNotFoundException("User not found with email: " + email));
        emailSend(user);
        return "Verify code sent";
    }

    @Override
    public UserResponseDto verify(VerifyDto verifyDto) {
        UserEntity userEntity = userRepository.findByEmail(verifyDto.getEmail())
                .orElseThrow(() -> new DataNotFoundException("User not found with email: " + verifyDto.getEmail()));
        Code passwords = userPasswordRepository.getUserPasswordById(userEntity.getId(),verifyDto.getCode())
                .orElseThrow(()-> new DataNotFoundException("Code is not found"));
        LocalDateTime currentTime = LocalDateTime.now();
        LocalDateTime sentDate = passwords.getSentDate();
        Duration duration = Duration.between(sentDate, currentTime);
        long minutes = duration.toMinutes();
        if(minutes <= passwords.getExpiry()) {
            userEntity.setIsAuthenticated(true);
            userRepository.save(userEntity);
            return parse(userEntity);
        }
        throw new AuthenticationCredentialsNotFoundException("Code is expired");
    }

    @Override
    public UserResponseDto editProfile(EditedProfileDto editedProfileDto, UUID appId) {
        AppEntity app = appService.findById(appId);
        if(editedProfileDto.getId() == null ) {
            throw new BadRequestException("User id must not be null");
        }
        UserEntity user = userRepository.findById(editedProfileDto.getId())
                .orElseThrow(() -> new DataNotFoundException("User not found with id: " + editedProfileDto.getId()));
        if(editedProfileDto.getBio().isPresent()) {
            user.setBio(editedProfileDto.getBio().get());
        }
        if(editedProfileDto.getUsername().isPresent()) {
            user.setUsername(editedProfileDto.getUsername().get());
        }
        if(editedProfileDto.getName().isPresent()) {
            user.setName(editedProfileDto.getName().get());
        }
        if(editedProfileDto.getPassword().isPresent()) {
            user.setPassword(editedProfileDto.getPassword().get());
        }
        userRepository.save(user);
        return parse(user);
    }

    private UserResponseDto parse(UserEntity user) {
        UserResponseDto map = modelMapper.map(user, UserResponseDto.class);
        map.setAppId(user.getAppEntity().getId());
        return map;
    }

    private UserEntity parse(UserCreateDto userCreateDto, UUID appId) {
        AppEntity app = appService.findById(appId);
        return new UserEntity(
                userCreateDto.getName(),
                userCreateDto.getEmail(),
                userCreateDto.getPassword(),
                app
        );
    }
//    public static Long generate12DigitNumber() {
//        Random random = new Random();
//        // 10^11 dan 10^12 gacha bo'lgan oraliqdagi tasodifiy son
//        return (long) (Math.pow(10, 11) + random.nextInt((int) (Math.pow(10, 12) - Math.pow(10, 11))));
//    }
}
