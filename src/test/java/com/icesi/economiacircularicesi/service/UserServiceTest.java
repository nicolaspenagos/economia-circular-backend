package com.icesi.economiacircularicesi.service;

import com.icesi.economiacircularicesi.model.TermsAndConditions;
import com.icesi.economiacircularicesi.model.User;
import com.icesi.economiacircularicesi.repository.TermsAndConditionsRepository;
import com.icesi.economiacircularicesi.repository.UserRepository;
import com.icesi.economiacircularicesi.service.impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.mock;

public class UserServiceTest {

    private UserRepository userRepository;
    private TermsAndConditionsRepository termsAndConditionsRepository;
    private UserServiceImpl userService;
    private User dummyUser;

    public void setupScenary(){

        dummyUser = new User(
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
                dummyUser
        );

        List<TermsAndConditions> termsAndCondsList = new ArrayList<>();
        termsAndCondsList.add(termsAndConditions);

        dummyUser.setTermsAndConditionsHistory(termsAndCondsList);

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
        when(userRepository.save(dummyUser)).thenReturn(dummyUser);
        userService.createUser(dummyUser);
        verify(userRepository, times(1)).save(dummyUser);
        verify(termsAndConditionsRepository, times(1)).save(dummyUser.getTermsAndConditionsHistory().get(0));
    }

    @Test
    public void getUsersTest(){
        userService.getUsers();
        verify(userRepository, times(1)).findAll();
    }

}
