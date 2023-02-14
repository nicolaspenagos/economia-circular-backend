package com.icesi.economiacircularicesi.utils;

import com.icesi.economiacircularicesi.constant.ErrorCode;
import com.icesi.economiacircularicesi.error.exception.CustomError.CustomError;
import com.icesi.economiacircularicesi.error.exception.CustomError.CustomException;
import org.springframework.http.HttpStatus;

public class ErrorExceptionUtils {

    public static void throwCustomException(HttpStatus httpStatus, ErrorCode code){
        throw new CustomException(httpStatus, new CustomError(code, code.getMessage()));
    }
}
