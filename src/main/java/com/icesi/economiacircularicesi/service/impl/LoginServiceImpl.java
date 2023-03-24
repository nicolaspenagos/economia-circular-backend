package com.icesi.economiacircularicesi.service.impl;

import com.icesi.economiacircularicesi.constant.ErrorCode;
import com.icesi.economiacircularicesi.dto.LoginDTO.LoginDTO;
import com.icesi.economiacircularicesi.dto.LoginDTO.TokenDTO;
import com.icesi.economiacircularicesi.error.exception.CustomError.CustomError;
import com.icesi.economiacircularicesi.error.exception.CustomError.CustomException;
import com.icesi.economiacircularicesi.model.User.User;
import com.icesi.economiacircularicesi.repository.UserRepository.UserRepository;
import com.icesi.economiacircularicesi.service.LoginService;
import com.icesi.economiacircularicesi.utils.JWTParser;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.StreamSupport;

@AllArgsConstructor
@Service
public class LoginServiceImpl implements LoginService {

    public final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @Override
    public TokenDTO login(LoginDTO loginDTO) {

        User user = StreamSupport.stream(userRepository.findAll().spliterator(),false)
                .filter(user1 -> user1.getEmail().equals(loginDTO.getEmail()))
                .findFirst()
                .orElseThrow(()->new CustomException(HttpStatus.UNAUTHORIZED, new CustomError(ErrorCode.CODE_A02_INVALID_EMAIL, ErrorCode.CODE_A02_INVALID_EMAIL.getMessage())));

        if(encoder.matches(loginDTO.getPassword(),user.getPassword())) {
            Map<String, String> claims = new HashMap<>();
            claims.put("userId", user.getId().toString());
            return new TokenDTO(JWTParser.createJWT(user.getId().toString(),user.getName(), user.getLastname(), claims,100000000L));
        }
        throw new CustomException(HttpStatus.UNAUTHORIZED, new CustomError(ErrorCode.CODE_A03_WRONG_PASSWORD, ErrorCode.CODE_A03_WRONG_PASSWORD.getMessage()));
    }
}