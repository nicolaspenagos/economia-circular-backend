package com.icesi.economiacircularicesi.model.Question;

public enum QuestionType {


    // MULTIPLE_CHOICE: Multiple selected options are allowed and the first option is always exclusive (scores zero)
    MULTIPLE_CHOICE,

    // INCREMENTAL_SINGLE_CHOICE: The first answer option does not score and the rest score incrementally, increasing their score in equivalent parts
    INCREMENTAL_SINGLE_CHOICE,

    // SINGLE_CHOICE: Only one selected option is allowed and the first option is always exclusive (scores zero)
    SINGLE_CHOICE

}
