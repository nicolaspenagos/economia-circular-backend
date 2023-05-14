package com.icesi.economiacircularicesi.utils;

import com.icesi.economiacircularicesi.constant.ErrorCode;
import com.icesi.economiacircularicesi.error.exception.custom_error.CustomError;
import com.icesi.economiacircularicesi.error.exception.custom_error.CustomException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "*")
public class ErrorExceptionUtils {

    @CrossOrigin(origins = "*")
    public static void throwCustomException(HttpStatus httpStatus, ErrorCode code){
        throw new CustomException(httpStatus, new CustomError(code, code.getMessage()));
    }

}
