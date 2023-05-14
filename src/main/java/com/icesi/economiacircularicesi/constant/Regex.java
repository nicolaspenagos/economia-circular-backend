package com.icesi.economiacircularicesi.constant;

public class Regex {

    private Regex(){

    }
    public static final String REGEX_EMAIL_PATTERN = "^(?=.{1,64}@)[\\p{L}0-9_-]+(\\.[\\p{L}0-9_-]+)*@"
            + "[^-][\\p{L}0-9-]+(\\.[\\p{L}0-9-]+)*(\\.[\\p{L}]{2,})$";
}
