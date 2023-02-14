package com.icesi.economiacircularicesi.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum UserErrorCode {

    CODE_U01_IMPOSSIBLE_DATE("Impossible date: the registrations or T&C acceptance date cannot be later than the current date"),
    CODE_U02_WRONG_DATE_FORMAT("Wrong date format: text cannot be parsed to a date, make sure you are using ISO 8601 yyyy-mm-ddThh:mm:ss format"),
    CODE_U03_INVALID_EMAIL("Invalid email: the provided email is not a valid email"),
    CODE_U04_DUPLICATED_EMAIL("Duplicate email: the provided email has already been registered"),
    CODE_U05_USER_NOT_FOUND("User not found: The user you refer to does not exist");

    private String message;

}
