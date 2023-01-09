package com.icesi.economiacircularicesi.controller;

import com.icesi.economiacircularicesi.dto.UserDTO;
import com.icesi.economiacircularicesi.mapper.UserMapper;
import com.icesi.economiacircularicesi.mapper.UserMapperImpl;
import com.icesi.economiacircularicesi.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.mockito.Mockito.*;

public class UserAPITest {

    private UserController userController;
    private UserMapper userMapper;
    private UserService userService;
    private UserDTO dummyUserDTO;

    public void setupScenary(){
        dummyUserDTO = new UserDTO(
                UUID.randomUUID(),
                "nicolas.penagosm98@gmail.com",
                "Nicolas",
                "Penagos",
                "Student",
                "Education",
                "Services",
                "2020-08-05T05:00:00.000"
        );


    }

    @BeforeEach
    public void init(){
        userMapper = new UserMapperImpl();
        userService = mock(UserService.class);
        userController = new UserController(userService, userMapper);
    }

    @Test
    public void createUserTest(){
        setupScenary();
        userController.createUser(dummyUserDTO);
        verify(userService, times(1)).createUser(userMapper.fromDTO(dummyUserDTO));
    }



}
