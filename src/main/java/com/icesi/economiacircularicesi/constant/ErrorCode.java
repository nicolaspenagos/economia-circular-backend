package com.icesi.economiacircularicesi.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ErrorCode {

    CODE_U01_IMPOSSIBLE_DATE("Impossible date: the registrations or T&C acceptance date cannot be later than the current date"),
    CODE_U02_WRONG_DATE_FORMAT("Wrong date format: text cannot be parsed to a date, make sure you are using ISO 8601 yyyy-mm-ddThh:mm:ss format"),
    CODE_U03_INVALID_EMAIL("Invalid email: the provided email is not a valid email"),
    CODE_U04_DUPLICATED_EMAIL("Duplicate email: the provided email has already been registered"),
    CODE_U05_USER_NOT_FOUND("User not found: The user you refer to does not exist"),
    CODE_U06_INVALID_PASSWORD("Invalid Password: "),
    CODE_E01_INVALID_ARGUMENTS("At least one property is invalid"),
    CODE_E02_NOT_PARSEABLE_JSON("HttpMessageNotReadableException: The JSON sent cannot be parsed"),
    CODE_Q01_QUESTION_NOT_FOUND("Question not found: The question you refer to does not exist"),

    CODE_Q02_INVALID_QUESTION_OPTIONS("Invalid questions options: the exclusiveness of the options does not correspond with the type of the quesion"),
    CODE_R01_ACTIVITY_WEIGHING_ERROR("Activity weighing error: the activity does not apply to the principle"),
    CODE_R02_RESPONSE_NOT_FOUND("Response not found: The response you refer to does not exist"),
    CODE_A01_NOT_AUTHENTICATED("Not authenticated: The user must be authenticated to perform this request"),
    CODE_A02_INVALID_EMAIL("Invalid email"),
    CODE_A03_WRONG_PASSWORD("Wrong password"),
    CODE_A04_UNAUTHORIZED("You are not authorized to make this request");

    private String message;

}
