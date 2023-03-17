package com.icesi.economiacircularicesi.test.utils;

import com.icesi.economiacircularicesi.model.Activity.Activity;
import com.icesi.economiacircularicesi.model.Principle.Principle;
import com.icesi.economiacircularicesi.model.Question.Question;
import com.icesi.economiacircularicesi.model.Question.QuestionOption;
import com.icesi.economiacircularicesi.model.Question.QuestionType;
import com.icesi.economiacircularicesi.model.Report.Score;
import com.icesi.economiacircularicesi.model.Response.Response;
import com.icesi.economiacircularicesi.model.Response.ResponseOption;
import com.icesi.economiacircularicesi.utils.ReportLogic;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;

import java.time.LocalDateTime;
import java.util.*;

public class ReportLogicTest {

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
    public final static UUID ACTIVITY_3_ID = UUID.randomUUID();

    public final static UUID QUESTION_1_ID = UUID.randomUUID();
    public final static UUID QUESTION_2_ID = UUID.randomUUID();
    public final static UUID QUESTION_3_ID = UUID.randomUUID();
    public final static UUID QUESTION_4_ID = UUID.randomUUID();
    public final static UUID QUESTION_5_ID = UUID.randomUUID();
    public final static UUID QUESTION_6_ID = UUID.randomUUID();
    public final static UUID QUESTION_7_ID = UUID.randomUUID();

    private ReportLogic reportLogic;
    private List<QuestionOption> questionOptions;
    private List<ResponseOption> selectedOptions;
    private List<ResponseOption> selectedOptions2;
    private List<Question> questions;
    private List<Activity> activities;
    private List<Principle> principles;


    @BeforeEach
    public void init(){
        reportLogic = new ReportLogic();
    }

    public void setupOptsAndResponses(){

        questionOptions = new ArrayList<>();

        // 5 question options
        questionOptions.add(new QuestionOption(QUESTION_OPT0_ID,0,"Opt sample 0",null, true, false));
        questionOptions.add(new QuestionOption(QUESTION_OPT1_ID,1,"Opt sample 1",null, false, false));
        questionOptions.add(new QuestionOption(QUESTION_OPT2_ID,2,"Opt sample 2",null, false, false));
        questionOptions.add(new QuestionOption(QUESTION_OPT3_ID,3,"Opt sample 3",null, false, false));
        questionOptions.add(new QuestionOption(QUESTION_OPT4_ID,4,"Opt sample 4",null, false, false));

        selectedOptions = new ArrayList<>();

        // 3 selected options
        selectedOptions.add(new ResponseOption(SELECTED_OPT0_ID, UUID.randomUUID(),QUESTION_OPT0_ID, null));
        selectedOptions.add(new ResponseOption(SELECTED_1PT0_ID, UUID.randomUUID(),QUESTION_OPT1_ID, null));
        selectedOptions.add(new ResponseOption(SELECTED_2PT0_ID, UUID.randomUUID(),QUESTION_OPT2_ID, null));


    }

    public void setupActivities(){

        activities = new ArrayList<>();

        activities.add(new Activity(ACTIVITY_1_ID,"Activity description", "ACTIVITY 1", "A1", 600.0, false));
        activities.add(new Activity(ACTIVITY_2_ID,"Activity description", "ACTIVITY 2", "A2", 400.0, false));
        activities.add(new Activity(ACTIVITY_3_ID,"Activity description", "ACTIVITY 3", "A3", 400.0, true));

    }

    public void setupPrinciples(){
        principles = new ArrayList<>();

        Set<Activity> p1Activities = new HashSet<>();
        p1Activities.add(activities.get(0));
        p1Activities.add(activities.get(1));
        p1Activities.add(activities.get(2));
        principles.add(new Principle(UUID.randomUUID(),"Principle description", "PRINCIPLE 1", "P1", 900, p1Activities));

        Set<Activity> p2Activities = new HashSet<>();
        p2Activities.add(activities.get(1));
        p2Activities.add(activities.get(2));
        principles.add(new Principle(UUID.randomUUID(),"Principle description", "PRINCIPLE 2", "P2", 200, p2Activities));
    }

    public void setupQuestions(){

        questions = new ArrayList<>();
        questions.add(new Question(QUESTION_1_ID, 0, "Question text 1",true, false, QuestionType.SINGLE_CHOICE, "",ACTIVITY_1_ID, questionOptions));
        questions.add(new Question(QUESTION_2_ID, 2, "Question text 2",true, false, QuestionType.INCREMENTAL_SINGLE_CHOICE, "",ACTIVITY_1_ID, questionOptions));
        questions.add(new Question(QUESTION_3_ID, 0, "Question text 3",true, false, QuestionType.SINGLE_CHOICE,"",ACTIVITY_2_ID, questionOptions));
        questions.add(new Question(QUESTION_4_ID, 3, "Question text 4",true, false, QuestionType.MULTIPLE_CHOICE,"",ACTIVITY_1_ID, questionOptions));

    }

    public void setUpSelectedOptions2(){

        selectedOptions2 = new ArrayList<>();

        selectedOptions2.add(new ResponseOption(UUID.randomUUID(),QUESTION_1_ID, QUESTION_OPT0_ID, null ));
        selectedOptions2.add(new ResponseOption(UUID.randomUUID(),QUESTION_2_ID, QUESTION_OPT4_ID, null ));
        selectedOptions2.add(new ResponseOption(UUID.randomUUID(),QUESTION_4_ID, QUESTION_OPT2_ID, null ));
        selectedOptions2.add(new ResponseOption(UUID.randomUUID(),QUESTION_4_ID, QUESTION_OPT3_ID, null ));
        selectedOptions2.add(new ResponseOption(UUID.randomUUID(),QUESTION_3_ID, QUESTION_OPT1_ID, null ));


    }

    public void setUpScenery3(){

        ArrayList<QuestionOption> dependentQuestionOptions = new ArrayList<>();

        // 5 question options
        dependentQuestionOptions.add(new QuestionOption(QUESTION_OPT0_ID,0,"Opt sample 0",null, true, true));
        dependentQuestionOptions.add(new QuestionOption(QUESTION_OPT1_ID,1,"Opt sample 1",null, true, false));
        dependentQuestionOptions.add(new QuestionOption(QUESTION_OPT2_ID,2,"Opt sample 2",null, false, false));

        questions.add(new Question(QUESTION_5_ID, 0, "Question text 5",true, false, QuestionType.SINGLE_CHOICE_DEPENDENT,"",ACTIVITY_3_ID, dependentQuestionOptions));
        questions.add(new Question(QUESTION_6_ID, 1, "Question text 6",true, false, QuestionType.SINGLE_CHOICE_DEPENDENT,"",ACTIVITY_3_ID, dependentQuestionOptions));
        questions.add(new Question(QUESTION_7_ID, 1, "Question text 7",true, false, QuestionType.SINGLE_CHOICE_DEPENDENT,"",ACTIVITY_3_ID, dependentQuestionOptions));

        selectedOptions2.add(new ResponseOption(UUID.randomUUID(),QUESTION_5_ID, QUESTION_OPT0_ID, null ));
        selectedOptions2.add(new ResponseOption(UUID.randomUUID(),QUESTION_6_ID, QUESTION_OPT1_ID, null ));
        selectedOptions2.add(new ResponseOption(UUID.randomUUID(),QUESTION_7_ID, QUESTION_OPT2_ID, null ));

    }




    @Test
    public void scoreMultipleChoiceTest(){

        setupOptsAndResponses();
        // expected: 0 = selected exclusive
        assertEquals(0.0, reportLogic.scoreMultipleChoice(400.0, questionOptions, selectedOptions));

        selectedOptions.remove(0);
        assertEquals(200.0, reportLogic.scoreMultipleChoice(400.0, questionOptions, selectedOptions));

    }

    @Test
    public void scoreIncrementalSingleChoiceTest(){

        setupOptsAndResponses();

        // expected = 0.0 (first option)
        assertEquals(0.0, reportLogic.scoreIncrementalSingleChoice(400, questionOptions, selectedOptions.get(0)));

        // expected = 200 (second option), score per option = 400/(5-1)
        assertEquals(200.0, reportLogic.scoreIncrementalSingleChoice(400, questionOptions, selectedOptions.get(2)));

    }

    @Test
    public void scoreSingleChoiceTest(){
        setupOptsAndResponses();
        assertEquals(0.0, reportLogic.scoreSingleChoice(400.0, questionOptions, selectedOptions.get(0)));

        assertEquals(400.0, reportLogic.scoreSingleChoice(400.0, questionOptions, selectedOptions.get(1)));
    }


    @Test
    public void scoreQuestionTest(){

        setupOptsAndResponses();
        setupActivities();
        setupQuestions();

        Question question = questions.get(0);

        List<ResponseOption> temp = new ArrayList<>();

        // MULTIPLE_CHOICE
        question.setType(QuestionType.MULTIPLE_CHOICE);
        assertEquals(0, reportLogic.scoreQuestion(question,selectedOptions, 400.0));

        temp.add(selectedOptions.get(1));
        temp.add(selectedOptions.get(2));
        assertEquals(0, reportLogic.scoreQuestion(question,selectedOptions, 400.0));

        temp.clear();
        temp.add(selectedOptions.get(0));

        // INCREMENTAL_SINGLE_CHOICE
        question.setType(QuestionType.INCREMENTAL_SINGLE_CHOICE);
        assertEquals(0.0, reportLogic.scoreQuestion(question,temp, 400.0));

        temp.clear();
        temp.add(selectedOptions.get(2));

        assertEquals(200.0, reportLogic.scoreQuestion(question,temp, 400.0));

        // SINGLE_CHOICE
        question.setType(QuestionType.SINGLE_CHOICE);

        temp.clear();
        temp.add(selectedOptions.get(0));

        assertEquals(0.0, reportLogic.scoreQuestion(question, temp, 400.0));

        temp.clear();
        temp.add(selectedOptions.get(2));

        assertEquals(400.0, reportLogic.scoreQuestion(question, temp, 400.0));

    }

    @Test
    public void getResponseOptionsMappedByActivityAndQuestionTest(){

        setupOptsAndResponses();
        setupActivities();
        setupQuestions();

        selectedOptions.get(0).setQuestionIdReference(QUESTION_1_ID);
        selectedOptions.get(1).setQuestionIdReference(QUESTION_1_ID);
        selectedOptions.get(2).setQuestionIdReference(QUESTION_2_ID);
        selectedOptions.add(new ResponseOption(UUID.randomUUID(),QUESTION_3_ID,SELECTED_2PT0_ID,null));
        Map<UUID, Map<UUID, List<ResponseOption>>> responseOptsByActivityMap = reportLogic.getResponseOptionsMappedByActivityAndQuestion(selectedOptions, activities, questions);

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
        setupActivities();
        setupQuestions();
        setUpSelectedOptions2();


        Activity activity = activities.get(0); // Getting ACTIVITY_1 from setup

        Map<UUID, List<ResponseOption>> responseOptionsByQuestions = reportLogic.getResponseOptionsMappedByActivityAndQuestion(selectedOptions2, activities, questions).get(activity.getId()); // Getting the map

        questions.remove(2); // To remove the question from activity 2
        Score activityScore = reportLogic.rateActivity(activities.get(0),responseOptionsByQuestions, questions, 0);

        assertEquals(activity.getName(),activityScore.getShortname());
        assertEquals(activity.getTitle(),activityScore.getTitle());
        assertEquals(activity.getScore(), activityScore.getPossibleScore());

        // QuestionTotalScore = 200
        // QuestionOptions = 5
        // Expected Score = (200.0/(5-1)*0)+(200.0/(5-1)*4)+(200.0/(5-1)*2) for the above configuration of selected options and questions
        double expectedScore = (200.0/(5-1)*0)+(200.0/(5-1)*4)+(200.0/(5-1)*2);

        assertEquals(expectedScore, activityScore.getObtainedScore());
        assertEquals(expectedScore/activity.getScore()*100, activityScore.getObtainedPercentage());

    }

    @Test
    public void getActivitiesScoreTest(){

        setupOptsAndResponses();
        setupActivities();
        setupQuestions();
        setUpSelectedOptions2();
        setUpScenery3();

        Response response = new Response(UUID.randomUUID(), LocalDateTime.now(), UUID.randomUUID() ,true, selectedOptions2);

        List<Score> activitiesScore = reportLogic.getActivitiesScore(activities, response, questions);

        Score score1 = activitiesScore.get(0);
        Score score2 = activitiesScore.get(1);


        assertThat(score1, hasProperty("shortname", is("A1")));
        assertThat(score1, hasProperty("title", is("ACTIVITY 1")));
        assertThat(score1, hasProperty("possibleScore", is(600.0)));
        assertThat(score1, hasProperty("obtainedScore", is(300.0)));
        assertThat(score1, hasProperty("obtainedPercentage", is(50.0)));

        assertThat(score2, hasProperty("shortname", is("A2")));
        assertThat(score2, hasProperty("title", is("ACTIVITY 2")));
        assertThat(score2, hasProperty("possibleScore", is(400.0)));
        assertThat(score2, hasProperty("obtainedScore", is(400.0)));
        assertThat(score2, hasProperty("obtainedPercentage", is(100.0)));

        Score score3 = activitiesScore.get(2);

        assertThat(score3, hasProperty("shortname", is("A3")));
        assertThat(score3, hasProperty("title", is("ACTIVITY 3")));
        assertThat(score3, hasProperty("possibleScore", is(400.0)));
        assertThat(score3, hasProperty("obtainedScore", is(200.0)));
        assertThat(score3, hasProperty("obtainedPercentage", is(50.0)));

    }

    @Test
    public void getActivityWeighingTest(){

        setupActivities();
        setupPrinciples();

        // Expected (900/3)/400
        assertEquals(0.75, reportLogic.getActivityWeighing(principles.get(0), activities.get(1)));
        assertEquals(0.25, reportLogic.getActivityWeighing(principles.get(1), activities.get(1)));

    }

}
