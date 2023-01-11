package com.icesi.economiacircularicesi.controller;

import com.icesi.economiacircularicesi.api.UserAPI;
import com.icesi.economiacircularicesi.constant.Regex;
import com.icesi.economiacircularicesi.constant.UserErrorCode;
import com.icesi.economiacircularicesi.dto.UserDTO;
import com.icesi.economiacircularicesi.error.exception.UserError;
import com.icesi.economiacircularicesi.error.exception.UserException;
import com.icesi.economiacircularicesi.mapper.UserMapper;
import com.icesi.economiacircularicesi.service.UserService;
import com.icesi.economiacircularicesi.utils.UserExceptionUtils;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
public class UserController implements UserAPI {

    public final UserService userService;
    public final UserMapper userMapper;

    @Override
    public UserDTO createUser(@Valid UserDTO userDTO) {

        // The first item in the list is validated as it necessarily refers
        // to the first acceptance of terms and conditions at the time of
        // user creation
        validateDate(userDTO.getTermsAndConditionsHistory().get(0).getAcceptanceDate());
        validateDate(userDTO.getRegistrationDate());
        validateEmail(userDTO.getEmail());
        return userMapper.fromUser(userService.createUser(userMapper.fromDTO(userDTO)));

    }

    @Override
    public List<UserDTO> getUsers() {

        return userService.getUsers().stream().map(userMapper::fromUser).collect(Collectors.toList());
    }


    public void validateDate(String date){

        try{
            LocalDateTime localDateTime = LocalDateTime.parse(date);
            if(localDateTime.isAfter(LocalDateTime.now())){
                UserExceptionUtils.throwUserException(HttpStatus.BAD_REQUEST, UserErrorCode.CODE_01_IMPOSSIBLE_DATE);
            }
        }catch (DateTimeParseException dateTimeParseException){
            UserExceptionUtils.throwUserException(HttpStatus.BAD_REQUEST, UserErrorCode.CODE_02_WRONG_DATE_FORMAT);
        }

    }

    public void validateEmail(String email){

        if(!email.matches(Regex.REGEX_EMAIL_PATTERN))
            UserExceptionUtils.throwUserException(HttpStatus.BAD_REQUEST, UserErrorCode.CODE_03_INVALID_EMAIL);

    }

}
