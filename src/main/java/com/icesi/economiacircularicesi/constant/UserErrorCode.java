package com.icesi.economiacircularicesi.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum UserErrorCode {

    CODE_01_IMPOSSIBLE_DATE("Impossible date: the acceptance date cannot be later than the current date"),
    CODE_02_WRONG_DATE_FORMAT("Wrong date format: text cannot be parsed to a date, make sure you are using ISO 8601 yyyy-mm-ddThh:mm:ss format");
    private String message;
}
