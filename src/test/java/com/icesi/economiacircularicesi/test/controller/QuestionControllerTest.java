package com.icesi.economiacircularicesi.test.controller;

import com.icesi.economiacircularicesi.constant.Question.BaseQuestion;
import com.icesi.economiacircularicesi.controller.QuestionController;
import com.icesi.economiacircularicesi.mapper.QuestionMapper;
import com.icesi.economiacircularicesi.mapper.QuestionMapperImpl;
import com.icesi.economiacircularicesi.service.QuestionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.mockito.Mockito.*;

public class QuestionControllerTest {

    private QuestionController questionController;
    private QuestionMapper questionMapper;
    private QuestionService questionService;

    @BeforeEach
    public void init(){
        questionMapper = new QuestionMapperImpl();
        questionService = mock(QuestionService.class);
        questionController = new QuestionController(questionService, questionMapper);
    }

    @Test
    public void createQuestionTest(){
        questionController.createQuestion(any());
        verify(questionService, times(1)).createQuestion(any());
    }

    @Test
    public void getQuestionTest(){
        UUID baseId = UUID.fromString(BaseQuestion.UUID.value);
        questionController.getQuestion(baseId);
        verify(questionService, times(1)).getQuestion(baseId);
    }

    @Test
    public void getQuestionsTest(){
        questionController.getQuestions();
        verify(questionService, times(1)).getQuestions();
    }

    @Test
    public void deleteQuestionTest(){
        UUID baseId = UUID.fromString(BaseQuestion.UUID.value);
        questionController.deleteQuestion(baseId);
        verify(questionService, times(1)).deleteQuestion(baseId);
    }

    @Test
    public void updateQuestionTest(){
        questionController.updateQuestion(any(), any());
        verify(questionService, times(1)).updateQuestion(any(), any());
    }
}