package com.icesi.economiacircularicesi.controller;

import com.icesi.economiacircularicesi.api.UserAPI;
import com.icesi.economiacircularicesi.dto.UserDTO;
import com.icesi.economiacircularicesi.mapper.UserMapper;
import com.icesi.economiacircularicesi.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.time.LocalDateTime;

@RestController
@AllArgsConstructor
public class UserController implements UserAPI {

    public final UserService userService;
    public final UserMapper userMapper;

    @Override
    public UserDTO createUser(@Valid UserDTO userDTO) {

        return userMapper.fromUser(userService.createUser(userMapper.fromDTO(userDTO)));
    }

    public void validateTermsAndConditionsAcceptanceDate(String date){
        LocalDateTime acceptanceDate = LocalDateTime.parse(date);
        LocalDateTime now = LocalDateTime.now();


    }
}
