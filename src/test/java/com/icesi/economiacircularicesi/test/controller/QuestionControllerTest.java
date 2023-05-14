package com.icesi.economiacircularicesi.test.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.icesi.economiacircularicesi.constant.Question.BaseQuestion;
import com.icesi.economiacircularicesi.controller.QuestionController;
import com.icesi.economiacircularicesi.dto.question.QuestionDTO;
import com.icesi.economiacircularicesi.mapper.QuestionMapper;
import com.icesi.economiacircularicesi.mapper.QuestionMapperImpl;
import com.icesi.economiacircularicesi.service.QuestionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static com.icesi.economiacircularicesi.utils.TestUtils.deserializeFromJsonFile;
import static org.mockito.Mockito.*;

class QuestionControllerTest {

    private QuestionController questionController;
    private QuestionMapper questionMapper;
    private QuestionService questionService;

    @BeforeEach
    void init() {
        questionMapper = new QuestionMapperImpl();
        questionService = mock(QuestionService.class);
        questionController = new QuestionController(questionService, questionMapper);
    }

    @Test
    void createQuestionTest() {
        QuestionDTO baseQuestionDTO = deserializeFromJsonFile("createQuestion.json", QuestionDTO.class, new ObjectMapper());

        questionController.createQuestion(baseQuestionDTO);
        verify(questionService, times(1)).createQuestion(any());
    }

    @Test
    void getQuestionTest() {
        UUID baseId = UUID.fromString(BaseQuestion.UUID.value);
        questionController.getQuestion(baseId);
        verify(questionService, times(1)).getQuestion(baseId);
    }

    @Test
    void getQuestionsTest() {
        questionController.getQuestions();
        verify(questionService, times(1)).getQuestions();
    }

    @Test
    void deleteQuestionTest() {
        UUID baseId = UUID.fromString(BaseQuestion.UUID.value);
        questionController.deleteQuestion(baseId);
        verify(questionService, times(1)).deleteQuestion(baseId);
    }

    @Test
    void updateQuestionTest() {
        questionController.updateQuestion(any(), any());
        verify(questionService, times(1)).updateQuestion(any(), any());
    }
}