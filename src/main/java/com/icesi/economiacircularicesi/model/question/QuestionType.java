package com.icesi.economiacircularicesi.model.question;

public enum QuestionType {


    // MULTIPLE_CHOICE: Multiple selected options are allowed and the first option is always exclusive (scores zero), the rest of the options scores in equals parts of the total score of the question
    MULTIPLE_CHOICE,

    // INCREMENTAL_SINGLE_CHOICE: The first answer option does not score and the rest score incrementally, increasing their score in equivalent parts
    INCREMENTAL_SINGLE_CHOICE,

    // SINGLE_CHOICE: Only one selected option is allowed and the first option is always exclusive (scores zero), the rest of the options scores the total score of the question (each)
    SINGLE_CHOICE,

    // SINGLE_CHOICE_DEPENDENT: Only one selected option is allowed and the first option is always exclusive (scores zero), the rest of the options scores the total score of the question (each). The total of the question is calculated depending on the counting of other selected options that apply to the product/service of the company. If a question of an activity contains this type of question, all the other questions of the activity must be this type.
    SINGLE_CHOICE_DEPENDENT

}
