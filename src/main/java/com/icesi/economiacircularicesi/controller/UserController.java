package com.icesi.economiacircularicesi.controller;

import com.icesi.economiacircularicesi.api.UserAPI;
import com.icesi.economiacircularicesi.constant.Regex;
import com.icesi.economiacircularicesi.constant.ErrorCode;
import com.icesi.economiacircularicesi.dto.UserDTO.TermsAndConditionsDTO;
import com.icesi.economiacircularicesi.dto.UserDTO.UserDTO;
import com.icesi.economiacircularicesi.dto.UserDTO.UserNoPassDTO;
import com.icesi.economiacircularicesi.mapper.UserMapper;
import com.icesi.economiacircularicesi.service.UserService;
import com.icesi.economiacircularicesi.utils.ErrorExceptionUtils;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
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
    public List<UserNoPassDTO> getUsers() {
        return userService.getUsers().stream().map(userMapper::fromUserToNoPass).collect(Collectors.toList());
    }

    @Override
    public UserNoPassDTO getUser(UUID userId) {
        return userMapper.fromUserToNoPass(userService.getUser(userId));
    }

    @Override
    public ResponseEntity<UUID> deleteUser(UUID userId) {
        return userService.deleteUser(userId);
    }

    @Override
    public UserNoPassDTO updateUser(UUID userId, UserDTO userDTO) {

        Optional.ofNullable(userDTO.getRegistrationDate()).ifPresent((registrationDate)->validateDate(registrationDate));
        Optional.ofNullable(userDTO.getEmail()).ifPresent((email)->validateEmail(email));
        Optional.ofNullable(userDTO.getTermsAndConditionsHistory()).ifPresent((tAndCList)->validateTermsAndCondsListDates(tAndCList));

        return userMapper.fromUserToNoPass(userService.updateUser(userId, userMapper.fromDTO(userDTO)));

    }

    private void validateDate(String date){
        try{
            LocalDateTime localDateTime = LocalDateTime.parse(date);
            if(localDateTime.isAfter(LocalDateTime.now(ZoneOffset.UTC))){
                ErrorExceptionUtils.throwCustomException(HttpStatus.BAD_REQUEST, ErrorCode.CODE_U01_IMPOSSIBLE_DATE);
            }
        }catch (DateTimeParseException dateTimeParseException){
            ErrorExceptionUtils.throwCustomException(HttpStatus.BAD_REQUEST, ErrorCode.CODE_U02_WRONG_DATE_FORMAT);
        }
    }

    private void validateTermsAndCondsListDates(List<TermsAndConditionsDTO> tAndCList){
        for(TermsAndConditionsDTO currentTAndC : tAndCList){
            validateDate(currentTAndC.getAcceptanceDate());
        }
    }

    private void validateEmail(String email){
        if(!email.matches(Regex.REGEX_EMAIL_PATTERN))
            ErrorExceptionUtils.throwCustomException(HttpStatus.BAD_REQUEST, ErrorCode.CODE_U03_INVALID_EMAIL);
    }

}
