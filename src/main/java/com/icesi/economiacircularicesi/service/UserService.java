package com.icesi.economiacircularicesi.service;

import com.icesi.economiacircularicesi.model.User;

import java.util.List;
import java.util.UUID;

public interface UserService {

    public User createUser(User user);
    public List<User> getUsers();
    public User getUser(UUID userId);
}
