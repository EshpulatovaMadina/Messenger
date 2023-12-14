package com.example.messenger.entity;

import com.example.messenger.entity.enums.ContentType;
import jakarta.persistence.*;

@Entity
public class MessageEntity extends BaseEntity{
    private String content;
    @Enumerated(EnumType.STRING)
    private ContentType type;
    @ManyToOne
    private UserEntity sender;
    @ManyToOne
    private ChatEntity chatEntity;
    @ManyToOne
    private AppEntity appEntity;
}
