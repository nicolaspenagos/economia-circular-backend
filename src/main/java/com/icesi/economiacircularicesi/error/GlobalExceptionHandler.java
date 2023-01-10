package com.icesi.economiacircularicesi.error;

import com.icesi.economiacircularicesi.error.exception.UserError;
import com.icesi.economiacircularicesi.error.exception.UserException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<UserError> handleException(UserException userException){
        return new ResponseEntity<>(userException.getError(), userException.getHttpStatus());
    }
}
