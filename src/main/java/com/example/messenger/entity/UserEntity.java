package com.example.messenger.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Random;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserEntity extends BaseEntity implements UserDetails {
    @Column(unique = true)
    @GeneratedValue
    private Long userId;
    private String name;
    @Column(unique = true)
    private String username;
    @Column(unique = true)
    private String email;
    private String password;
    private String bio;
    private String photo;

    public UserEntity( String name, String email, String password, AppEntity appEntity) {
        this.userId = generate12DigitNumber();
        this.name = name;
        this.username = username;
        this.email = email;
        this.password = password;
        this.appEntity = appEntity;
    }

    public UserEntity(String name, String username, String email, String password, String bio, String photo, AppEntity appEntity, Boolean isAuthenticated) {
        this.userId = generate12DigitNumber();
        this.name = name;
        this.username = username;
        this.email = email;
        this.password = password;
        this.bio = bio;
        this.photo = photo;
        this.appEntity = appEntity;
        this.isAuthenticated = isAuthenticated;
    }

    @ManyToOne
    private AppEntity appEntity;
    private Boolean isAuthenticated = false;

    public static Long generate12DigitNumber() {
        Random random = new Random();
        // 10^11 dan 10^12 gacha bo'lgan oraliqdagi tasodifiy son
        return (long) (Math.pow(10, 11) + random.nextInt((int) (Math.pow(10, 12) - Math.pow(10, 11))));
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
