package com.icesi.economiacircularicesi.controller;

import com.icesi.economiacircularicesi.api.LoginAPI;
import com.icesi.economiacircularicesi.dto.login.LoginDTO;
import com.icesi.economiacircularicesi.dto.login.TokenDTO;
import com.icesi.economiacircularicesi.service.LoginService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
public class LoginController implements LoginAPI {


    private final LoginService loginService;

    @Override
    public TokenDTO login(LoginDTO loginDTO) {
        return loginService.login(loginDTO);
    }
}