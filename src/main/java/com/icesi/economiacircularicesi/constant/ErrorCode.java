package com.icesi.economiacircularicesi.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ErrorCode {

    CODE_E01_INVALID_ARGUMENTS("At least one property is invalid");
    private String message;

}
