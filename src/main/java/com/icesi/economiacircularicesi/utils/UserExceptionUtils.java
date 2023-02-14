package com.icesi.economiacircularicesi.utils;

import com.icesi.economiacircularicesi.constant.UserErrorCode;
import com.icesi.economiacircularicesi.error.exception.UserError.UserError;
import com.icesi.economiacircularicesi.error.exception.UserError.UserException;
import org.springframework.http.HttpStatus;

public class UserExceptionUtils {

    public static void throwUserException(HttpStatus httpStatus, UserErrorCode code){
        throw new UserException(httpStatus, new UserError(code, code.getMessage()));
    }
}
