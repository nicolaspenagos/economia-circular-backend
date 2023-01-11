package com.icesi.economiacircularicesi.controller;

import com.icesi.economiacircularicesi.constant.UserErrorCode;
import com.icesi.economiacircularicesi.dto.TermsAndConditionsDTO;
import com.icesi.economiacircularicesi.dto.UserDTO;
import com.icesi.economiacircularicesi.error.exception.UserException;
import com.icesi.economiacircularicesi.mapper.UserMapper;
import com.icesi.economiacircularicesi.mapper.UserMapperImpl;
import com.icesi.economiacircularicesi.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UserControllerTest {

    private UserController userController;
    private UserMapper userMapper;
    private UserService userService;
    private UserDTO dummyUserDTO;

    public void setupScenary(){

        TermsAndConditionsDTO termsAndConditionsDTO = new TermsAndConditionsDTO(UUID.randomUUID(), "2020-08-05T05:00:00.000", "www.link.com");
        List<TermsAndConditionsDTO> termsAndCondsList = new ArrayList<>();
        termsAndCondsList.add(termsAndConditionsDTO);

        dummyUserDTO = new UserDTO(
                UUID.randomUUID(),
                "jhon.doe@email.com",
                "Jhon",
                "Doe",
                "Student",
                "Education",
                "Services",
                "2020-08-05T05:00:00.000",
                termsAndCondsList
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

    @Test
    public void invalidEmailTestNoAt(){
        setupScenary();
        dummyUserDTO.setEmail("jhon.doeemail.com");
        verifyCreateUserExceptionThrown(UserErrorCode.CODE_03_INVALID_EMAIL, dummyUserDTO);
    }

    @Test
    public void invalidEmailTestNoLocalPart(){
        setupScenary();
        dummyUserDTO.setEmail("@email.com");
        verifyCreateUserExceptionThrown(UserErrorCode.CODE_03_INVALID_EMAIL, dummyUserDTO);
    }
    @Test
    public void invalidEmailTestNoDomain(){
        setupScenary();
        dummyUserDTO.setEmail("jhon.doe@");
        verifyCreateUserExceptionThrown(UserErrorCode.CODE_03_INVALID_EMAIL, dummyUserDTO);
    }

    @Test
    public void impossibleRegistrationDateTest(){
        setupScenary();
        dummyUserDTO.setRegistrationDate(generateFutureDate().toString());
        verifyCreateUserExceptionThrown(UserErrorCode.CODE_01_IMPOSSIBLE_DATE, dummyUserDTO);
    }

    @Test
    public void wrongRegistrationDateFormatTest(){
        setupScenary();
        dummyUserDTO.setRegistrationDate("Tuesday, January 10, 2023");
        verifyCreateUserExceptionThrown(UserErrorCode.CODE_02_WRONG_DATE_FORMAT, dummyUserDTO);
    }
    @Test
    public void impossibleTAndCAcceptanceTest(){
        setupScenary();
        dummyUserDTO.getTermsAndConditionsHistory().get(0).setAcceptanceDate(generateFutureDate().toString());
        verifyCreateUserExceptionThrown(UserErrorCode.CODE_01_IMPOSSIBLE_DATE, dummyUserDTO);
    }

    @Test
    public void wrongTAndCAcceptanceFormatTest(){
        setupScenary();
        dummyUserDTO.getTermsAndConditionsHistory().get(0).setAcceptanceDate("Tuesday, January 10, 2023");
        verifyCreateUserExceptionThrown(UserErrorCode.CODE_02_WRONG_DATE_FORMAT, dummyUserDTO);
    }

    @Test
    public void getUsersTest(){
        userController.getUsers();
        verify(userService, times(1)).getUsers();
    }

    private void verifyCreateUserExceptionThrown(UserErrorCode expectedCode, UserDTO userDTO) {

        // Check if the corresponding exception is thrown when we are trying to
        // create a user containing an invalid attribute
        try {
            userController.createUser(userDTO);
            fail();
        } catch (UserException exception) {

            assertEquals(HttpStatus.BAD_REQUEST, exception.getHttpStatus());
            assertNotNull(exception.getError());
            assertEquals(expectedCode.getMessage(), exception.getError().getMessage());
            assertEquals(expectedCode, exception.getError().getCode());

        }

    }

    public LocalDateTime generateFutureDate(){
        long currentTime = System.currentTimeMillis();
        long futureTime = currentTime + 1000L;
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(futureTime), ZoneId.systemDefault());
    }

}
