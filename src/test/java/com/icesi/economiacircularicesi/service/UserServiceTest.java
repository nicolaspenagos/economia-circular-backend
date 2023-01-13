package com.icesi.economiacircularicesi.service;

import com.icesi.economiacircularicesi.constant.UserErrorCode;
import com.icesi.economiacircularicesi.dto.UserDTO;
import com.icesi.economiacircularicesi.error.exception.UserException;
import com.icesi.economiacircularicesi.model.TermsAndConditions;
import com.icesi.economiacircularicesi.model.User;
import com.icesi.economiacircularicesi.repository.TermsAndConditionsRepository;
import com.icesi.economiacircularicesi.repository.UserRepository;
import com.icesi.economiacircularicesi.service.impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.mock;

public class UserServiceTest {

    private UserRepository userRepository;
    private TermsAndConditionsRepository termsAndConditionsRepository;
    private UserServiceImpl userService;
    private User baseUser;

    public void setupScenary(){

        baseUser = new User(
                UUID.randomUUID(),
                "jhon.doe@email.com",
                "Jhon",
                "Doe",
                "Student",
                "Education",
                "Services",
                LocalDateTime.parse("2020-08-05T05:00:00.000"),
                null
        );

        TermsAndConditions termsAndConditions = new TermsAndConditions(
                UUID.randomUUID(),
                LocalDateTime.parse("2020-08-05T05:00:00.000"),
                "www.link.com",
                baseUser
        );

        List<TermsAndConditions> termsAndCondsList = new ArrayList<>();
        termsAndCondsList.add(termsAndConditions);

        baseUser.setTermsAndConditionsHistory(termsAndCondsList);

    }

    @BeforeEach
    private void init(){
        userRepository = mock(UserRepository.class);
        termsAndConditionsRepository = mock(TermsAndConditionsRepository.class);
        userService = new UserServiceImpl(userRepository, termsAndConditionsRepository);
    }

    @Test
    public void createUserTest(){
        setupScenary();
        when(userRepository.save(baseUser)).thenReturn(baseUser);
        userService.createUser(baseUser);
        verify(userRepository, times(1)).save(baseUser);
        verify(termsAndConditionsRepository, times(1)).save(baseUser.getTermsAndConditionsHistory().get(0));
    }

    @Test
    public void getUsersTest(){
        userService.getUsers();
        verify(userRepository, times(1)).findAll();
    }

    @Test
    public void duplicatedEmailTest(){
        setupScenary();

        List<User> baseUsers = new ArrayList<>();
        baseUsers.add(baseUser);
        when(userService.getUsers()).thenReturn(baseUsers);
        verifyCreateUserExceptionThrown(UserErrorCode.CODE_04_DUPLICATED_EMAIL ,baseUser);

    }

    private void verifyCreateUserExceptionThrown(UserErrorCode expectedCode, User user) {

        // Check if the corresponding exception is thrown when we are trying to
        // create a user containing an invalid attribute
        try {
            userService.createUser(user);
            fail();
        } catch (UserException exception) {

            assertEquals(HttpStatus.BAD_REQUEST, exception.getHttpStatus());
            assertNotNull(exception.getError());
            assertEquals(expectedCode.getMessage(), exception.getError().getMessage());
            assertEquals(expectedCode, exception.getError().getCode());

        }

    }

}
