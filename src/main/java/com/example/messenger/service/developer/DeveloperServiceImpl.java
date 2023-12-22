package com.example.messenger.service.developer;

import com.example.messenger.DTO.request.DevCreateDto;
import com.example.messenger.DTO.request.MailDto;
import com.example.messenger.DTO.response.AppResponseDto;
import com.example.messenger.DTO.response.DevResponseDto;
import com.example.messenger.entity.AppEntity;
import com.example.messenger.entity.DevPassword;
import com.example.messenger.entity.DeveloperEntity;
import com.example.messenger.exception.DataAlreadyExistsException;
import com.example.messenger.exception.DataNotFoundException;
import com.example.messenger.repository.DevPasswordRepository;
import com.example.messenger.repository.DevRepository;
import com.example.messenger.repository.UserPasswordRepository;
import com.example.messenger.service.MailService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DeveloperServiceImpl implements DeveloperService {
    private final DevRepository devRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final DevPasswordRepository devPasswordRepository;
    private final MailService mailService;
//    private final AppService appService;
    @Override
    public DevResponseDto save(DevCreateDto dto) {
        DeveloperEntity developerEntity = parse(dto);
        Optional<DeveloperEntity> optional = devRepository.findByEmail(dto.getEmail());
        if(optional.isPresent()) {
            throw new DataAlreadyExistsException("Developer is already exists with email: " + dto.getEmail());
        }
        developerEntity.setPassword(passwordEncoder.encode(dto.getPassword()));
        DeveloperEntity save = devRepository.save(developerEntity);
        emailSend(developerEntity);
        return parse(save);
    }

    @Override
    public void emailSend(DeveloperEntity developerEntity) {
        String generatedString = RandomStringUtils.randomAlphanumeric(5);
        System.err.println("generatedString = " + generatedString);
        MailDto mailDto = new MailDto(generatedString, developerEntity.getEmail());
        mailService.sendMail(mailDto);
        devPasswordRepository.save(new DevPassword(generatedString, LocalDateTime.now(),3,developerEntity));
    }
    @Override
    public DeveloperEntity findById(UUID developerId) {
        return devRepository.findById(developerId)
                .orElseThrow(()-> new DataNotFoundException("Developer not found with id: "+developerId));
    }


    public AppResponseDto parse(AppEntity appEntity) {
        return new AppResponseDto(
                appEntity.getId(),
                appEntity.getId(),
                appEntity.getAppName()
        );
    }
    private DevResponseDto parse(DeveloperEntity entity) {

        DevResponseDto devResponseDto = new DevResponseDto(
                entity.getId(),
                entity.getEmail(),
                entity.getPassword(),
                null
        );

        if(entity.getAppEntities() != null && !entity.getAppEntities().isEmpty()) {
            List<AppResponseDto> list = entity.getAppEntities().stream().map(this::parse).toList();
            devResponseDto.setApps(list);
        }
        return devResponseDto;
    }

    private DeveloperEntity parse(DevCreateDto dto) {
        //        entity.setAppKey(generate12DigitNumber());
        return modelMapper.map(dto, DeveloperEntity.class);
    }

//    public static Integer generate12DigitNumber() {
//        Random random = new Random();
//        return (int) (Math.pow(10, 11) + random.nextInt((int) Math.pow(10, 12) - (int) Math.pow(10, 11)));
//    }

}
