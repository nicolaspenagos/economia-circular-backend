package com.icesi.economiacircularicesi.service.impl;

import com.icesi.economiacircularicesi.constant.ErrorCode;
import com.icesi.economiacircularicesi.dto.login.LoginDTO;
import com.icesi.economiacircularicesi.dto.login.TokenDTO;
import com.icesi.economiacircularicesi.error.exception.custom_error.CustomError;
import com.icesi.economiacircularicesi.error.exception.custom_error.CustomException;
import com.icesi.economiacircularicesi.model.user.User;
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

import static com.icesi.economiacircularicesi.constant.TokenExpTimes.ONE_DAY;

@AllArgsConstructor
@Service
public class LoginServiceImpl implements LoginService {

    private final UserRepository userRepository;
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
            return new TokenDTO(JWTParser.createJWT(user.getId().toString(),user.getName(), user.getOrganization(), claims, ONE_DAY));
        }
        throw new CustomException(HttpStatus.UNAUTHORIZED, new CustomError(ErrorCode.CODE_A03_WRONG_PASSWORD, ErrorCode.CODE_A03_WRONG_PASSWORD.getMessage()));
    }
}