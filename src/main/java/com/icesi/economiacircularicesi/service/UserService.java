package com.icesi.economiacircularicesi.service;

import com.icesi.economiacircularicesi.model.User;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

public interface UserService {

    public User createUser(User user);
    public List<User> getUsers();
    public User getUser(UUID userId);
    public ResponseEntity<UUID> deleteUser(UUID userId);
    public User updateUser(UUID userId, User user);
}
