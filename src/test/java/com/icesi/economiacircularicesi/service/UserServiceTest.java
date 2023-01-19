package com.icesi.economiacircularicesi.service;

import com.icesi.economiacircularicesi.constant.UserErrorCode;
import com.icesi.economiacircularicesi.constants.BaseUser;
import com.icesi.economiacircularicesi.dto.UserDTO;
import com.icesi.economiacircularicesi.error.exception.UserException;
import com.icesi.economiacircularicesi.model.TermsAndConditions;
import com.icesi.economiacircularicesi.model.User;
import com.icesi.economiacircularicesi.repository.TermsAndConditionsRepository;
import com.icesi.economiacircularicesi.repository.UserRepository;
import com.icesi.economiacircularicesi.service.impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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

    private PasswordEncoder passwordEncoder;

    public void setupScenary(){

        baseUser = new User(
                UUID.fromString(BaseUser.UUID.value),
                BaseUser.EMAIL.value,
                BaseUser.PASSWORD.value,
                BaseUser.NAME.value,
                BaseUser.LASTNAME.value,
                BaseUser.POSITION.value,
                BaseUser.SECTOR.value,
                BaseUser.MACROSECTOR.value,
                BaseUser.ORGANIZATION.value,
                LocalDateTime.parse(BaseUser.DATE.value),
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
        passwordEncoder = mock(PasswordEncoder.class);
        userService = new UserServiceImpl(userRepository, termsAndConditionsRepository, passwordEncoder);

    }

    @Test
    public void createUserTest(){

        setupScenary();
        when(userRepository.save(baseUser)).thenReturn(baseUser);
        userService.createUser(baseUser);
        verify(userRepository, times(1)).save(baseUser);
        verify(termsAndConditionsRepository, times(1)).save(baseUser.getTermsAndConditionsHistory().get(0));
        verify(passwordEncoder, times(1)).encode(BaseUser.PASSWORD.value);

    }

    @Test
    public void getUsersTest(){
        userService.getUsers();
        verify(userRepository, times(1)).findAll();
    }

    @Test
    public void getUserTest(){
        userService.getUser(UUID.fromString(BaseUser.UUID.value));
        verify(userRepository, times(1)).findById(UUID.fromString(BaseUser.UUID.value));
    }

    @Test
    public void deleteUserTest(){

        setupScenary();
        when(userRepository.findById(baseUser.getUserId())).thenReturn(Optional.of(baseUser));

        userService.deleteUser(baseUser.getUserId());

        verify(termsAndConditionsRepository, times(1)).delete(baseUser.getTermsAndConditionsHistory().get(0));
        verify(userRepository, times(baseUser.getTermsAndConditionsHistory().size())).delete(baseUser);

    }

    @Test
    public void userNotFoundDeleteTest(){

        setupScenary();
        when(userRepository.findById(baseUser.getUserId())).thenReturn(Optional.ofNullable(null));

        try{

            userService.deleteUser(baseUser.getUserId());
            fail();

        }catch (UserException exception){

            assertEquals(HttpStatus.BAD_REQUEST, exception.getHttpStatus());
            assertNotNull(exception.getError());
            assertEquals(UserErrorCode.CODE_05_USER_NOT_FOUND.getMessage(), exception.getError().getMessage());
            assertEquals(UserErrorCode.CODE_05_USER_NOT_FOUND, exception.getError().getCode());

        }

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
