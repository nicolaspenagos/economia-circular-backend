package com.icesi.economiacircularicesi.constants.Question;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum BaseQuestion {

    UUID("dbcecc3d-5bb5-4b05-89d7-973b0169b576"),
    TEXT("This is a question test"),
    IS_MANDOTORY("true"),
    JUSTIFY("true"),
    ORDER("1"),
    TYPE("MULTIPLE_CHOICE"),
    ACTIVITY_ID("b8ea1fea-776e-4060-91d9-b361ef839a47");

    public final String value;

}
