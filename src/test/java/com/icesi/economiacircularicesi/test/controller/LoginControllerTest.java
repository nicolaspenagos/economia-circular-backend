package com.icesi.economiacircularicesi.test.controller;

import com.icesi.economiacircularicesi.controller.LoginController;
import com.icesi.economiacircularicesi.service.LoginService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class LoginControllerTest {

    private LoginController loginController;
    private LoginService loginService;

    @BeforeEach
    void init(){
        loginService = mock(LoginService.class);
        loginController = new LoginController(loginService);
    }

    @Test
    void loginTest(){
        loginController.login(any());
        verify(loginService, times(1)).login(any());
    }
}
