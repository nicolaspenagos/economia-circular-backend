package com.icesi.economiacircularicesi.controller;

import com.icesi.economiacircularicesi.api.UserAPI;
import com.icesi.economiacircularicesi.constant.UserErrorCode;
import com.icesi.economiacircularicesi.dto.UserDTO;
import com.icesi.economiacircularicesi.error.exception.UserError;
import com.icesi.economiacircularicesi.error.exception.UserException;
import com.icesi.economiacircularicesi.mapper.UserMapper;
import com.icesi.economiacircularicesi.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

@RestController
@AllArgsConstructor
public class UserController implements UserAPI {

    public final UserService userService;
    public final UserMapper userMapper;

    @Override
    public UserDTO createUser(@Valid UserDTO userDTO) {

        validateTermsAndConditionsAcceptanceDate(userDTO.getTermsAndConditionsHistory().get(0).getAcceptanceDate());
        return userMapper.fromUser(userService.createUser(userMapper.fromDTO(userDTO)));

    }

    public void validateTermsAndConditionsAcceptanceDate(String date){

        try{
            LocalDateTime acceptanceDate = LocalDateTime.parse(date);
            LocalDateTime now = LocalDateTime.now();
            if(acceptanceDate.isAfter(now)){
                throw new UserException(HttpStatus.BAD_REQUEST, new UserError(UserErrorCode.CODE_01_IMPOSSIBLE_DATE, UserErrorCode.CODE_01_IMPOSSIBLE_DATE.getMessage()));
            }
        }catch (DateTimeParseException dateTimeParseException){
            throw new UserException(HttpStatus.BAD_REQUEST, new UserError(UserErrorCode.CODE_02_WRONG_DATE_FORMAT, UserErrorCode.CODE_02_WRONG_DATE_FORMAT.getMessage()));
        }

    }
}
