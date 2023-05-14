package com.icesi.economiacircularicesi.error.exception.CustomError;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public class CustomException extends RuntimeException{

    private final HttpStatus httpStatus;
    private final CustomError error;

}
