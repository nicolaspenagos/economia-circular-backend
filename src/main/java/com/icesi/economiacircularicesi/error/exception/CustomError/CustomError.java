package com.icesi.economiacircularicesi.error.exception.CustomError;

import com.icesi.economiacircularicesi.constant.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class CustomError implements Serializable {
    private ErrorCode code;
    private String message;
}
