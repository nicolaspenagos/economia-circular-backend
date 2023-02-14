package com.icesi.economiacircularicesi.error.exception.GeneralError;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GeneralError {
    private String error;
    private String message;
}
