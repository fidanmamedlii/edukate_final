package com.example.edukate.dtos.replydto;

import lombok.Data;

@Data
public class ContactCommentDto {
    private String name;
    private String email;
    private String subject;
    private String message;
}
