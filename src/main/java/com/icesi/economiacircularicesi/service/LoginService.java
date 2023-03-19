package com.icesi.economiacircularicesi.service;

import com.icesi.economiacircularicesi.dto.LoginDTO.LoginDTO;
import com.icesi.economiacircularicesi.dto.LoginDTO.TokenDTO;

public interface LoginService {

    TokenDTO login(LoginDTO loginDTO);
}
