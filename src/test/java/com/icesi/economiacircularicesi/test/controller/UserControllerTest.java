package com.icesi.economiacircularicesi.test.controller;

import com.icesi.economiacircularicesi.constant.ErrorCode;
import com.icesi.economiacircularicesi.controller.UserController;
import com.icesi.economiacircularicesi.dto.user.TermsAndConditionsDTO;
import com.icesi.economiacircularicesi.dto.user.UserDTO;
import com.icesi.economiacircularicesi.error.exception.custom_error.CustomException;
import com.icesi.economiacircularicesi.mapper.UserMapper;
import com.icesi.economiacircularicesi.mapper.UserMapperImpl;
import com.icesi.economiacircularicesi.service.UserService;
import com.icesi.economiacircularicesi.constant.User.BaseTermsAndCondsAcceptance;
import com.icesi.economiacircularicesi.constant.User.BaseUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserControllerTest {

    private UserController userController;
    private UserMapper userMapper;
    private UserService userService;
    private UserDTO baseUserDTO;

    void setupScenary() {

        TermsAndConditionsDTO termsAndConditionsDTO = new TermsAndConditionsDTO(UUID.fromString(BaseTermsAndCondsAcceptance.UUID.value), BaseTermsAndCondsAcceptance.DATE.value, BaseTermsAndCondsAcceptance.LINK.value);
        List<TermsAndConditionsDTO> termsAndCondsList = new ArrayList<>();
        termsAndCondsList.add(termsAndConditionsDTO);

        baseUserDTO = new UserDTO(
                UUID.fromString(BaseUser.UUID.value),
                BaseUser.EMAIL.value,
                BaseUser.PASSWORD.value,
                BaseUser.NAME.value,
                BaseUser.POSITION.value,
                BaseUser.SECTOR.value,
                BaseUser.MACROSECTOR.value,
                BaseUser.ORGANIZATION.value,
                BaseUser.DATE.value,
                termsAndCondsList
        );

    }

    @BeforeEach
    void init() {
        userMapper = new UserMapperImpl();
        userService = mock(UserService.class);
        userController = new UserController(userService, userMapper);
    }

    @Test
    void createUserTest() {
        setupScenary();
        userController.createUser(baseUserDTO);
        verify(userService, times(1)).createUser(userMapper.fromDTO(baseUserDTO));
    }

    @Test
    void invalidEmailTestNoAt() {
        setupScenary();
        baseUserDTO.setEmail("jhon.doeemail.com");
        verifyCreateUserExceptionThrown(ErrorCode.CODE_U03_INVALID_EMAIL, baseUserDTO);
    }

    @Test
    void invalidEmailTestNoLocalPart() {
        setupScenary();
        baseUserDTO.setEmail("@email.com");
        verifyCreateUserExceptionThrown(ErrorCode.CODE_U03_INVALID_EMAIL, baseUserDTO);
    }

    @Test
    void invalidEmailTestNoDomain() {
        setupScenary();
        baseUserDTO.setEmail("jhon.doe@");
        verifyCreateUserExceptionThrown(ErrorCode.CODE_U03_INVALID_EMAIL, baseUserDTO);
    }

    @Test
    void impossibleRegistrationDateTest() {
        setupScenary();
        baseUserDTO.setRegistrationDate(LocalDateTime.now().plusDays(1).toString());
        verifyCreateUserExceptionThrown(ErrorCode.CODE_U01_IMPOSSIBLE_DATE, baseUserDTO);
    }

    @Test
    void wrongRegistrationDateFormatTest() {
        setupScenary();
        baseUserDTO.setRegistrationDate("Tuesday, January 10, 2023");
        verifyCreateUserExceptionThrown(ErrorCode.CODE_U02_WRONG_DATE_FORMAT, baseUserDTO);
    }

    @Test
    void impossibleTAndCAcceptanceTest() {
        setupScenary();
        baseUserDTO.getTermsAndConditionsHistory().get(0).setAcceptanceDate(LocalDateTime.now().plusDays(1).toString());
        verifyCreateUserExceptionThrown(ErrorCode.CODE_U01_IMPOSSIBLE_DATE, baseUserDTO);
    }

    @Test
    void wrongTAndCAcceptanceFormatTest() {
        setupScenary();
        baseUserDTO.getTermsAndConditionsHistory().get(0).setAcceptanceDate("Tuesday, January 10, 2023");
        verifyCreateUserExceptionThrown(ErrorCode.CODE_U02_WRONG_DATE_FORMAT, baseUserDTO);
    }

    @Test
    void getUsersTest() {
        userController.getUsers();
        verify(userService, times(1)).getUsers();
    }

    @Test
    void getUserTest() {
        userController.getUser(UUID.fromString(BaseUser.UUID.value));
        verify(userService, times(1)).getUser(UUID.fromString(BaseUser.UUID.value));
    }

    @Test
    void deleteUserTest() {
        userController.deleteUser(UUID.fromString(BaseUser.UUID.value));
        verify(userService, times(1)).deleteUser(UUID.fromString(BaseUser.UUID.value));
    }

    @Test
    void updateUserTest() {
        setupScenary();
        userController.updateUser(baseUserDTO.getId(), baseUserDTO);
        verify(userService, times(1)).updateUser(baseUserDTO.getId(), userMapper.fromDTO(baseUserDTO));
    }

    private void verifyCreateUserExceptionThrown(ErrorCode expectedCode, UserDTO userDTO) {

        // Check if the corresponding exception is thrown when we are trying to
        // create a user containing an invalid attribute
        try {
            userController.createUser(userDTO);
            fail();
        } catch (CustomException exception) {

            assertEquals(HttpStatus.BAD_REQUEST, exception.getHttpStatus());
            assertNotNull(exception.getError());
            assertEquals(expectedCode.getMessage(), exception.getError().getMessage());
            assertEquals(expectedCode, exception.getError().getCode());

        }

    }

}
