package com.example.messenger.entity;

import com.example.messenger.entity.enums.ContentType;
import jakarta.persistence.*;

@Entity
public class Message extends BaseEntity{
    private String content;
    @Enumerated(EnumType.STRING)
    private ContentType type;
    @ManyToOne
    private User sender;
    @ManyToOne
    private Chat chat;
    @ManyToOne
    private App app;
}
