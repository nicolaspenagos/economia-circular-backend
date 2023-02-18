package com.icesi.economiacircularicesi.constants.Question;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum BaseQuestion {

    UUID("dbcecc3d-5bb5-4b05-89d7-973b0169b576"),
    TEXT("This is a question test"),
    IS_MANDOTORY("true"),
    ORDER("1"),
    TYPE("MULTIPLE_CHOICE"),
    ACTIVITY("A1");

    public final String value;

}
