package com.icesi.economiacircularicesi.error.exception;

import com.icesi.economiacircularicesi.constant.UserErrorCode;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserError {
    private UserErrorCode code;
    private String message;
}
