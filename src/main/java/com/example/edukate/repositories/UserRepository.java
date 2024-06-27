package com.example.edukate.repositories;


import com.example.edukate.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
    User findByEmail(String email);
    User findByConfirmationToken(String confirmationToken);

}