package com.icesi.economiacircularicesi.test.utils;

import com.icesi.economiacircularicesi.model.question.Question;
import com.icesi.economiacircularicesi.model.user.User;
import com.icesi.economiacircularicesi.utils.ReportLogic;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import static org.junit.jupiter.api.Assertions.*;

class  ServiceUtilsTest {

    private UUID id;
    private List<User> usersList;

    private List<Question> questionsList;

    void setupUsersScenery(){

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

    void setupQuestionsScenery(){

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
    void getIndexOfTestWithUsers(){

        setupUsersScenery();
        usersList.get(0).setId(id);

        int index = ReportLogic.getIndexOf(usersList, id);
        assertEquals(0, index );
        assertEquals("Nicolas", usersList.get(index).getName());
        assertEquals(-1, ReportLogic.getIndexOf(usersList, UUID.randomUUID()));


    }

    @Test
    void getIndexOfTestWithQuestions(){

        setupQuestionsScenery();
        questionsList.get(2).setId(id);

        int index = ReportLogic.getIndexOf(questionsList, id);
        assertEquals(2, index);
        assertEquals("Txt3" , questionsList.get(index).getQuestionText());
        assertEquals( -1, ReportLogic.getIndexOf(questionsList, UUID.randomUUID()));

    }











}
