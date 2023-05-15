package com.icesi.economiacircularicesi.test.service;

import com.icesi.economiacircularicesi.constant.ErrorCode;
import com.icesi.economiacircularicesi.dto.login.LoginDTO;
import com.icesi.economiacircularicesi.error.exception.custom_error.CustomException;
import com.icesi.economiacircularicesi.model.user.User;
import com.icesi.economiacircularicesi.repository.user_repository.UserRepository;
import com.icesi.economiacircularicesi.service.impl.LoginServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class LoginServiceTest {
    private UserRepository userRepository;
    private LoginServiceImpl loginService;

    private ArrayList<User> users;

    private void setupScenery(){
        User user = new User(
                "user@email.com",
                "$2a$10$IoVx1iFkIDQ2VFL7E2ey3e/pgyEA8NoGimS0vsFMm0ZjNXdvkxePW",
                "Jhon",
                "Teacher",
                "Education",
                "Services",
                "Icesi",
                null,
                null
        );
        user.setId(UUID.randomUUID());
        users = new ArrayList<>();
        users.add(user);
    }

    @BeforeEach
    private void init() {
        userRepository = mock(UserRepository.class);
        loginService = new LoginServiceImpl(userRepository);
    }

    @Test
    void loginTest() {
        setupScenery();
        when(userRepository.findAll()).thenReturn(users);
        assertNotNull(loginService.login(new LoginDTO("user@email.com","password")));
    }

    @Test
    void loginWrongPassword(){
        setupScenery();
        when(userRepository.findAll()).thenReturn(users);
        try{
            loginService.login(new LoginDTO("user@email.com","wrongPassword"));
        }catch (CustomException exception){
            assertEquals(HttpStatus.UNAUTHORIZED, exception.getHttpStatus());
            assertNotNull(exception.getError());
            assertEquals(ErrorCode.CODE_A03_WRONG_PASSWORD.getMessage(), exception.getError().getMessage());
            assertEquals(ErrorCode.CODE_A03_WRONG_PASSWORD, exception.getError().getCode());
        }
    }
}
