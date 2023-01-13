package com.icesi.economiacircularicesi.controller;

import com.icesi.economiacircularicesi.constant.UserErrorCode;
import com.icesi.economiacircularicesi.dto.TermsAndConditionsDTO;
import com.icesi.economiacircularicesi.dto.UserDTO;
import com.icesi.economiacircularicesi.error.exception.UserException;
import com.icesi.economiacircularicesi.mapper.UserMapper;
import com.icesi.economiacircularicesi.mapper.UserMapperImpl;
import com.icesi.economiacircularicesi.service.UserService;
import com.icesi.economiacircularicesi.constants.BaseTermsAndCondsAcceptance;
import com.icesi.economiacircularicesi.constants.BaseUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.icesi.economiacircularicesi.utils.TestUtils.generateFutureDate;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UserControllerTest {

    private UserController userController;
    private UserMapper userMapper;
    private UserService userService;
    private UserDTO baseUserDTO;

    public void setupScenary(){

        TermsAndConditionsDTO termsAndConditionsDTO = new TermsAndConditionsDTO(UUID.fromString(BaseTermsAndCondsAcceptance.UUID.value), BaseTermsAndCondsAcceptance.DATE.value, BaseTermsAndCondsAcceptance.LINK.value);
        List<TermsAndConditionsDTO> termsAndCondsList = new ArrayList<>();
        termsAndCondsList.add(termsAndConditionsDTO);

        baseUserDTO = new UserDTO(
                UUID.fromString(BaseUser.UUID.value),
                BaseUser.EMAIL.value,
                BaseUser.NAME.value,
                BaseUser.LASTNAME.value,
                BaseUser.POSITION.value,
                BaseUser.SECTOR.value,
                BaseUser.MACROSECTOR.value,
                BaseUser.DATE.value,
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
        userController.createUser(baseUserDTO);
        verify(userService, times(1)).createUser(userMapper.fromDTO(baseUserDTO));
    }

    @Test
    public void invalidEmailTestNoAt(){
        setupScenary();
        baseUserDTO.setEmail("jhon.doeemail.com");
        verifyCreateUserExceptionThrown(UserErrorCode.CODE_03_INVALID_EMAIL, baseUserDTO);
    }

    @Test
    public void invalidEmailTestNoLocalPart(){
        setupScenary();
        baseUserDTO.setEmail("@email.com");
        verifyCreateUserExceptionThrown(UserErrorCode.CODE_03_INVALID_EMAIL, baseUserDTO);
    }
    @Test
    public void invalidEmailTestNoDomain(){
        setupScenary();
        baseUserDTO.setEmail("jhon.doe@");
        verifyCreateUserExceptionThrown(UserErrorCode.CODE_03_INVALID_EMAIL, baseUserDTO);
    }

    @Test
    public void impossibleRegistrationDateTest(){
        setupScenary();
        baseUserDTO.setRegistrationDate(generateFutureDate().toString());
        verifyCreateUserExceptionThrown(UserErrorCode.CODE_01_IMPOSSIBLE_DATE, baseUserDTO);
    }

    @Test
    public void wrongRegistrationDateFormatTest(){
        setupScenary();
        baseUserDTO.setRegistrationDate("Tuesday, January 10, 2023");
        verifyCreateUserExceptionThrown(UserErrorCode.CODE_02_WRONG_DATE_FORMAT, baseUserDTO);
    }
    @Test
    public void impossibleTAndCAcceptanceTest(){
        setupScenary();
        baseUserDTO.getTermsAndConditionsHistory().get(0).setAcceptanceDate(generateFutureDate().toString());
        verifyCreateUserExceptionThrown(UserErrorCode.CODE_01_IMPOSSIBLE_DATE, baseUserDTO);
    }

    @Test
    public void wrongTAndCAcceptanceFormatTest(){
        setupScenary();
        baseUserDTO.getTermsAndConditionsHistory().get(0).setAcceptanceDate("Tuesday, January 10, 2023");
        verifyCreateUserExceptionThrown(UserErrorCode.CODE_02_WRONG_DATE_FORMAT, baseUserDTO);
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

}
