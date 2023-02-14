package com.icesi.economiacircularicesi.error.exception.CustomError;

import com.icesi.economiacircularicesi.constant.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CustomError {
    private ErrorCode code;
    private String message;
}
