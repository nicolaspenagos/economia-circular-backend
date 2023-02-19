package com.icesi.economiacircularicesi.service;

import com.icesi.economiacircularicesi.model.User.User;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

public interface UserService {

    User createUser(User user);
    List<User> getUsers();
    User getUser(UUID userId);
    ResponseEntity<UUID> deleteUser(UUID userId);
    User updateUser(UUID userId, User user);
}
