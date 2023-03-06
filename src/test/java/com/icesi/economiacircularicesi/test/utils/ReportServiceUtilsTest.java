package com.icesi.economiacircularicesi.test.utils;

import com.icesi.economiacircularicesi.model.Question.QuestionOption;
import com.icesi.economiacircularicesi.model.Response.ResponseOption;
import com.icesi.economiacircularicesi.utils.ReportServiceUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ReportServiceUtilsTest {


    private ReportServiceUtils reportServiceUtils;
    private List<QuestionOption> questionOptions;
    private List<ResponseOption> selectedOptions;

    @BeforeEach
    public void init(){
        reportServiceUtils = new ReportServiceUtils();
    }

    public void setupScenery(){

        questionOptions = new ArrayList<>();

        // 5 question options
        UUID questionOpt0 = UUID.randomUUID();
        questionOptions.add(new QuestionOption(questionOpt0,0,"Opt sample 0",null));

        UUID questionOpt1 = UUID.randomUUID();
        questionOptions.add(new QuestionOption(questionOpt1,1,"Opt sample 1",null));

        UUID questionOpt2 = UUID.randomUUID();
        questionOptions.add(new QuestionOption(questionOpt2,2,"Opt sample 2",null));

        UUID questionOpt3 = UUID.randomUUID();
        questionOptions.add(new QuestionOption(questionOpt3,3,"Opt sample 3",null));

        UUID questionOpt4 = UUID.randomUUID();
        questionOptions.add(new QuestionOption(questionOpt4,4,"Opt sample 4",null));

        selectedOptions = new ArrayList<>();

        // 3 selected options
        selectedOptions.add(new ResponseOption(UUID.randomUUID(), UUID.randomUUID(),questionOpt0, null));

        selectedOptions.add(new ResponseOption(UUID.randomUUID(), UUID.randomUUID(),questionOpt1, null));

        selectedOptions.add(new ResponseOption(UUID.randomUUID(), UUID.randomUUID(),questionOpt2, null));


    }


    @Test
    public void ScoreMultipleChoiceTest(){

        setupScenery();
        // expected: 400/5*3 = 240
        assertEquals(240, reportServiceUtils.scoreMultipleChoice(400.0, questionOptions, selectedOptions));

    }

    @Test
    public void scoreIncrementalSingleAnswerTest(){

        setupScenery();

        // expected = 0.0 (first option)
        assertEquals(0.0, reportServiceUtils.scoreIncrementalSingleAnswer(400, questionOptions, selectedOptions.get(0)));

        // expected = 200 (second option), score per option = 400/(5-1)
        assertEquals(200.0, reportServiceUtils.scoreIncrementalSingleAnswer(400, questionOptions, selectedOptions.get(2)));

    }



}
