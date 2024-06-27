package com.example.edukate.models;

import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import lombok.*;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "contact_messages")
@Transactional
public class ContactMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String subject;
    private String message;
    private String reply;
    private boolean replied;

}
