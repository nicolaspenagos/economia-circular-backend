package com.icesi.economiacircularicesi.service;

import com.icesi.economiacircularicesi.model.User;

import java.util.List;

public interface UserService {

    public User createUser(User user);
    public List<User> getUsers();
}
