package com.example.chatserver.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Document
@Getter
@Setter
public class Chat {
    @Id
    private String id;
    private String detail;
    private String user;
    private LocalDateTime createDate;
}
