package com.icesi.economiacircularicesi.service;

import com.icesi.economiacircularicesi.dto.login.LoginDTO;
import com.icesi.economiacircularicesi.dto.login.TokenDTO;

public interface LoginService {

    TokenDTO login(LoginDTO loginDTO);
}
