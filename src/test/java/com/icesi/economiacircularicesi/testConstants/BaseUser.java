package com.icesi.economiacircularicesi.testConstants;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor

public enum BaseUser {

    UUID("f89a5360-39d6-43ad-8798-ef9666666715"),
    NAME("Jhon"),
    LASTNAME("Doe"),
    EMAIL("jhon.doe@email.com"),
    POSITION("Student"),
    SECTOR("Education"),
    MACROSECTOR("Services"),
    DATE("2022-02-02T05:00:00");

    public final String value;

}


