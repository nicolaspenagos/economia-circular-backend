package com.icesi.economiacircularicesi.test.utils;

import com.icesi.economiacircularicesi.model.Activity.Activity;
import com.icesi.economiacircularicesi.model.Question.Question;
import com.icesi.economiacircularicesi.model.Question.QuestionOption;
import com.icesi.economiacircularicesi.model.Question.QuestionType;
import com.icesi.economiacircularicesi.model.Report.Score;
import com.icesi.economiacircularicesi.model.Response.ResponseOption;
import com.icesi.economiacircularicesi.utils.ReportServiceUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class ReportServiceUtilsTest {


    private ReportServiceUtils reportServiceUtils;
    private List<QuestionOption> questionOptions;
    private List<ResponseOption> selectedOptions;
    private List<ResponseOption> responseOptions;
    private List<Question> questions;

    private List<Activity> activities;

    public final static UUID QUESTION_OPT0_ID = UUID.randomUUID();
    public final static UUID QUESTION_OPT1_ID = UUID.randomUUID();
    public final static UUID QUESTION_OPT2_ID = UUID.randomUUID();
    public final static UUID QUESTION_OPT3_ID = UUID.randomUUID();
    public final static UUID QUESTION_OPT4_ID = UUID.randomUUID();

    public final static UUID SELECTED_OPT0_ID = UUID.randomUUID();
    public final static UUID SELECTED_1PT0_ID = UUID.randomUUID();
    public final static UUID SELECTED_2PT0_ID = UUID.randomUUID();

    public final static UUID ACTIVITY_1_ID = UUID.randomUUID();
    public final static UUID ACTIVITY_2_ID = UUID.randomUUID();

    public final static UUID QUESTION_1_ID = UUID.randomUUID();
    public final static UUID QUESTION_2_ID = UUID.randomUUID();
    public final static UUID QUESTION_3_ID = UUID.randomUUID();
    public final static UUID QUESTION_4_ID = UUID.randomUUID();





    @BeforeEach
    public void init(){
        reportServiceUtils = new ReportServiceUtils();
    }

    public void setupOptsAndResponses(){

        questionOptions = new ArrayList<>();

        // 5 question options
        questionOptions.add(new QuestionOption(QUESTION_OPT0_ID,0,"Opt sample 0",null, true));
        questionOptions.add(new QuestionOption(QUESTION_OPT1_ID,1,"Opt sample 1",null, false));
        questionOptions.add(new QuestionOption(QUESTION_OPT2_ID,2,"Opt sample 2",null, false));
        questionOptions.add(new QuestionOption(QUESTION_OPT3_ID,3,"Opt sample 3",null, false));
        questionOptions.add(new QuestionOption(QUESTION_OPT4_ID,4,"Opt sample 4",null, false));

        selectedOptions = new ArrayList<>();

        // 3 selected options
        selectedOptions.add(new ResponseOption(SELECTED_OPT0_ID, UUID.randomUUID(),QUESTION_OPT0_ID, null));
        selectedOptions.add(new ResponseOption(SELECTED_1PT0_ID, UUID.randomUUID(),QUESTION_OPT1_ID, null));
        selectedOptions.add(new ResponseOption(SELECTED_2PT0_ID, UUID.randomUUID(),QUESTION_OPT2_ID, null));

        responseOptions = new ArrayList<>();

    }

    public void setupActivity(){

        activities = new ArrayList<>();

        activities.add(new Activity(ACTIVITY_1_ID,"Activity description", "ACTIVITY 1", "A1", 600.0, false));

        activities.add(new Activity(ACTIVITY_2_ID,"Activity description", "ACTIVITY 2", "A2", 400.0, false));

    }

    public void setupQuestions(){

        questions = new ArrayList<>();


        questions.add(new Question(QUESTION_1_ID, 0, "Question text",true, false, QuestionType.SINGLE_CHOICE,ACTIVITY_1_ID, questionOptions));

        questions.add(new Question(QUESTION_2_ID, 2, "Question text 2",true, false, QuestionType.INCREMENTAL_SINGLE_CHOICE,ACTIVITY_1_ID, questionOptions));

        questions.add(new Question(QUESTION_3_ID, 0, "Question text 3",true, false, QuestionType.SINGLE_CHOICE,ACTIVITY_2_ID, null));

        questions.add(new Question(QUESTION_4_ID, 3, "Question text 3",true, false, QuestionType.MULTIPLE_CHOICE,ACTIVITY_1_ID, questionOptions));

    }


    @Test
    public void scoreMultipleChoiceTest(){

        setupOptsAndResponses();
        // expected: 0 = selected exclusive
        assertEquals(0.0, reportServiceUtils.scoreMultipleChoice(400.0, questionOptions, selectedOptions));

        selectedOptions.remove(0);
        assertEquals(200.0, reportServiceUtils.scoreMultipleChoice(400.0, questionOptions, selectedOptions));

    }

    @Test
    public void scoreIncrementalSingleChoiceTest(){

        setupOptsAndResponses();

        // expected = 0.0 (first option)
        assertEquals(0.0, reportServiceUtils.scoreIncrementalSingleChoice(400, questionOptions, selectedOptions.get(0)));

        // expected = 200 (second option), score per option = 400/(5-1)
        assertEquals(200.0, reportServiceUtils.scoreIncrementalSingleChoice(400, questionOptions, selectedOptions.get(2)));

    }

    @Test
    public void scoreSingleChoiceTest(){
        setupOptsAndResponses();
        assertEquals(0.0, reportServiceUtils.scoreSingleChoice(400.0, questionOptions, selectedOptions.get(0)));

        assertEquals(400.0, reportServiceUtils.scoreSingleChoice(400.0, questionOptions, selectedOptions.get(1)));
    }


    @Test
    public void scoreQuestionTest(){

        setupOptsAndResponses();
        setupActivity();
        setupQuestions();

        Question question = questions.get(0);

        List<ResponseOption> temp = new ArrayList<>();

        // MULTIPLE_CHOICE
        question.setType(QuestionType.MULTIPLE_CHOICE);
        assertEquals(0, reportServiceUtils.scoreQuestion(question,selectedOptions, 400.0));

        temp.add(selectedOptions.get(1));
        temp.add(selectedOptions.get(2));
        assertEquals(0, reportServiceUtils.scoreQuestion(question,selectedOptions, 400.0));

        temp.clear();
        temp.add(selectedOptions.get(0));

        // INCREMENTAL_SINGLE_CHOICE
        question.setType(QuestionType.INCREMENTAL_SINGLE_CHOICE);
        assertEquals(0.0, reportServiceUtils.scoreQuestion(question,temp, 400.0));

        temp.clear();
        temp.add(selectedOptions.get(2));

        assertEquals(200.0, reportServiceUtils.scoreQuestion(question,temp, 400.0));

        // SINGLE_CHOICE
        question.setType(QuestionType.SINGLE_CHOICE);

        temp.clear();
        temp.add(selectedOptions.get(0));

        assertEquals(0.0, reportServiceUtils.scoreQuestion(question, temp, 400.0));

        temp.clear();
        temp.add(selectedOptions.get(2));

        assertEquals(400.0, reportServiceUtils.scoreQuestion(question, temp, 400.0));

    }

    @Test
    public void getResponseOptionsMappedByActivityAndQuestionTest(){

        setupOptsAndResponses();
        setupActivity();
        setupQuestions();

        selectedOptions.get(0).setQuestionIdReference(QUESTION_1_ID);
        selectedOptions.get(1).setQuestionIdReference(QUESTION_1_ID);
        selectedOptions.get(2).setQuestionIdReference(QUESTION_2_ID);
        selectedOptions.add(new ResponseOption(UUID.randomUUID(),QUESTION_3_ID,SELECTED_2PT0_ID,null));
        Map<UUID, Map<UUID, List<ResponseOption>>> responseOptsByActivityMap = reportServiceUtils.getResponseOptionsMappedByActivityAndQuestion(selectedOptions, activities, questions);

        ArrayList<ResponseOption> temp = new ArrayList<>();
        temp.add(selectedOptions.get(0));
        temp.add(selectedOptions.get(1));
        assertEquals(temp, responseOptsByActivityMap.get(ACTIVITY_1_ID).get(QUESTION_1_ID));

        temp.clear();
        temp.add(selectedOptions.get(2));
        assertEquals(temp, responseOptsByActivityMap.get(ACTIVITY_1_ID).get(QUESTION_2_ID));

        temp.clear();
        temp.add(selectedOptions.get(3));
        assertEquals(temp, responseOptsByActivityMap.get(ACTIVITY_2_ID).get(QUESTION_3_ID));

    }

    @Test
    public void rateActivityTest(){

        setupOptsAndResponses();
        setupActivity();
        setupQuestions();

        ArrayList<ResponseOption> tempSelectedOpts = new ArrayList<>();

        tempSelectedOpts.add(new ResponseOption(UUID.randomUUID(),QUESTION_1_ID, QUESTION_OPT0_ID, null ));
        tempSelectedOpts.add(new ResponseOption(UUID.randomUUID(),QUESTION_2_ID, QUESTION_OPT4_ID, null ));
        tempSelectedOpts.add(new ResponseOption(UUID.randomUUID(),QUESTION_4_ID, QUESTION_OPT2_ID, null ));
        tempSelectedOpts.add(new ResponseOption(UUID.randomUUID(),QUESTION_4_ID, QUESTION_OPT3_ID, null ));

        Activity activity = activities.get(0); // Getting ACTIVITY_1 from setup

        Map<UUID, List<ResponseOption>> responseOptionsByQuestions = reportServiceUtils.getResponseOptionsMappedByActivityAndQuestion(tempSelectedOpts, activities, questions).get(activity.getId()); // Getting the map

        Score activityScore = reportServiceUtils.rateActivity(activities.get(0),responseOptionsByQuestions, questions);

        assertEquals(activity.getName(),activityScore.getShortName());
        assertEquals(activity.getTitle(),activityScore.getTitle());
        assertEquals(activity.getScore(), activityScore.getPossibleScore());

        // QuestionTotalScore = 200
        // QuestionOptions = 5
        // Expected Score = (200.0/(5-1)*0)+(200.0/(5-1)*4)+(200.0/(5-1)*2) for the above configuration of selected options and questions
        double expectedScore = (200.0/(5-1)*0)+(200.0/(5-1)*4)+(200.0/(5-1)*2);

        assertEquals(expectedScore, activityScore.getObtainedScore());
        assertEquals(expectedScore/activity.getScore()*100, activityScore.getObtainedPercentage());

    }


}
