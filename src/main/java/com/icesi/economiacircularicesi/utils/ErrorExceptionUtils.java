package com.icesi.economiacircularicesi.utils;

import com.icesi.economiacircularicesi.constant.UserErrorCode;
import com.icesi.economiacircularicesi.error.exception.CustomError.CustomError;
import com.icesi.economiacircularicesi.error.exception.CustomError.CustomException;
import org.springframework.http.HttpStatus;

public class UserExceptionUtils {

    public static void throwUserException(HttpStatus httpStatus, UserErrorCode code){
        throw new CustomException(httpStatus, new CustomError(code, code.getMessage()));
    }
}
