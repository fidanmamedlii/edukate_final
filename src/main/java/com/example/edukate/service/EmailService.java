package com.example.edukate.service;

public interface EmailService {
    void sendConfirmationEmail(String email, String token);
    public void sendPasswordResetEmail(String email, String token);

}
