package com.example.messenger.DTO.request;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Optional;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class EditedProfileDto {

    private UUID id;
    private Optional<String> name = Optional.empty();
    private Optional<String> username = Optional.empty();
    private Optional<String> email = Optional.empty();
    private Optional<String> password = Optional.empty();
    private Optional<String> bio = Optional.empty();

    // Constructors
    public EditedProfileDto(UUID id) {
        this.id = id;
    }

    // Additional constructor if needed
    public EditedProfileDto(UUID id, String name, String username, String email, String password, String bio) {
        this.id = id;
        this.name = Optional.ofNullable(name);
        this.username = Optional.ofNullable(username);
        this.email = Optional.ofNullable(email);
        this.password = Optional.ofNullable(password);
        this.bio = Optional.ofNullable(bio);
    }
}
