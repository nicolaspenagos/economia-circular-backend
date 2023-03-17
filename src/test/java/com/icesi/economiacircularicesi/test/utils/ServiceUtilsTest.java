package com.icesi.economiacircularicesi.test.utils;

import com.icesi.economiacircularicesi.model.Question.Question;
import com.icesi.economiacircularicesi.model.User.User;
import com.icesi.economiacircularicesi.utils.ReportLogic;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import static org.junit.jupiter.api.Assertions.*;

public class ServiceUtilsTest {

    private UUID id;
    private List<User> usersList;

    private List<Question> questionsList;

    public void setupUsersScenery(){

        id = UUID.randomUUID();
        usersList = new ArrayList<>();

        User firstUser = User.builder().name("Nicolas").build();
        firstUser.setId(UUID.randomUUID());
        usersList.add(firstUser);

        User secondUser = User.builder().name("Jose").build();
        secondUser.setId(UUID.randomUUID());
        usersList.add(secondUser);

        User thirdUser = User.builder().name("Ana").build();
        thirdUser.setId(UUID.randomUUID());
        usersList.add(thirdUser);

    }

    public void setupQuestionsScenery(){

        id = UUID.randomUUID();
        questionsList = new ArrayList<>();

        Question firstQuestion = Question.builder().questionText("Txt1").build();
        firstQuestion.setId(UUID.randomUUID());
        questionsList.add(firstQuestion);

        Question secondQuestion = Question.builder().questionText("Txt2").build();
        secondQuestion.setId(UUID.randomUUID());
        questionsList.add(secondQuestion);

        Question thirdQuestion = Question.builder().questionText("Txt3").build();
        thirdQuestion.setId(UUID.randomUUID());
        questionsList.add(thirdQuestion);

    }

    @Test
    public void getIndexOfTestWithUsers(){

        setupUsersScenery();
        usersList.get(0).setId(id);

        int index = ReportLogic.getIndexOf(usersList, id);
        assertTrue(index == 0);
        assertTrue(usersList.get(index).getName().equals("Nicolas"));

        assertTrue(ReportLogic.getIndexOf(usersList, UUID.randomUUID())==-1);

    }

    @Test
    public void getIndexOfTestWithQuestions(){

        setupQuestionsScenery();
        questionsList.get(2).setId(id);

        int index = ReportLogic.getIndexOf(questionsList, id);
        assertTrue(index == 2);
        assertTrue(questionsList.get(index).getQuestionText().equals("Txt3"));

        assertTrue(ReportLogic.getIndexOf(questionsList, UUID.randomUUID())==-1);

    }











}
