package com.icesi.economiacircularicesi.service;

import com.icesi.economiacircularicesi.constant.ErrorCode;
import com.icesi.economiacircularicesi.constants.Question.BaseQuestion;
import com.icesi.economiacircularicesi.constants.Question.BaseQuestionOption;
import com.icesi.economiacircularicesi.error.exception.CustomError.CustomException;
import com.icesi.economiacircularicesi.mapper.QuestionMapper;
import com.icesi.economiacircularicesi.model.Question.Question;
import com.icesi.economiacircularicesi.model.Question.QuestionOption;
import com.icesi.economiacircularicesi.model.Question.QuestionType;
import com.icesi.economiacircularicesi.repository.QuestionRepository.QuestionOptionRepository;
import com.icesi.economiacircularicesi.repository.QuestionRepository.QuestionRepository;
import com.icesi.economiacircularicesi.service.impl.QuestionServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class QuestionServiceTest {

    private QuestionMapper questionMapper;
    private QuestionRepository questionRepository;
    private QuestionOptionRepository questionOptionRepository;
    private QuestionServiceImpl questionService;
    private Question baseQuestion;

    public void setupScenary(){

        baseQuestion = new Question(UUID.fromString(BaseQuestion.UUID.value), Integer.parseInt(BaseQuestion.ORDER.value), BaseQuestion.TEXT.value, Boolean.valueOf(BaseQuestion.IS_MANDOTORY.value), Boolean.valueOf(BaseQuestion.JUSTIFY.value),QuestionType.valueOf(BaseQuestion.TYPE.value), BaseQuestion.ACTIVITY.value, null);

        QuestionOption questionOption = new QuestionOption(UUID.fromString(BaseQuestionOption.UUID.value), Integer.parseInt(BaseQuestionOption.ORDER.value), BaseQuestionOption.VALUE.value, baseQuestion);

        List<QuestionOption> options = new ArrayList<>();
        options.add(questionOption);

        baseQuestion.setQuestionOptions(options);

    }

    @BeforeEach
    private void init(){

        questionMapper = mock(QuestionMapper.class);
        questionRepository = mock(QuestionRepository.class);
        questionOptionRepository = mock(QuestionOptionRepository.class);
        questionService = new QuestionServiceImpl(questionRepository, questionOptionRepository, questionMapper);

    }

    @Test
    public void createQuestionTest(){

        setupScenary();

        when(questionRepository.save(baseQuestion)).thenReturn(baseQuestion);
        questionService.createQuestion(baseQuestion);
        verify(questionRepository, times(1)).save(baseQuestion);
        verify(questionOptionRepository, times(1)).save(baseQuestion.getQuestionOptions().get(0));

    }

    @Test
    public void getQuestionsTest(){
        questionService.getQuestions();
        verify(questionRepository, times(1)).findAll();
    }

    @Test
    public void getQuestionTest(){
        setupScenary();
        questionService.getQuestion(baseQuestion.getQuestionId());
        verify(questionRepository, times(1)).findById(baseQuestion.getQuestionId());
    }

    @Test
    public void deleteQuestionTest(){

        setupScenary();

        when(questionRepository.findById(baseQuestion.getQuestionId())).thenReturn(Optional.of(baseQuestion));
        questionService.deleteQuestion(baseQuestion.getQuestionId());
        verify(questionRepository, times(1)).delete(baseQuestion);
        verify(questionOptionRepository, times(1)).delete(baseQuestion.getQuestionOptions().get(0));

    }

    @Test
    public void questionNotFoundDeleteTest(){

        setupScenary();
        when(questionRepository.findById(baseQuestion.getQuestionId())).thenReturn(Optional.ofNullable(null));

        try{

            questionService.deleteQuestion(baseQuestion.getQuestionId());
            fail();

        }catch (CustomException exception){

            assertEquals(HttpStatus.BAD_REQUEST, exception.getHttpStatus());
            assertNotNull(exception.getError());
            assertEquals(ErrorCode.CODE_Q01_QUESTION_NOT_FOUND.getMessage(), exception.getError().getMessage());
            assertEquals(ErrorCode.CODE_Q01_QUESTION_NOT_FOUND, exception.getError().getCode());

        }
    }

    @Test
    public void updateQuestionTest(){

        setupScenary();
        when(questionRepository.findById(baseQuestion.getQuestionId())).thenReturn(Optional.of(baseQuestion));
        doNothing().when(questionMapper).updateQuestionFromQuestion(any(), any());

        questionService.updateQuestion(baseQuestion.getQuestionId(), baseQuestion);
        verify(questionRepository, times(1)).findById(baseQuestion.getQuestionId());
        verify(questionRepository, times(1)).save(baseQuestion);
        verify(questionOptionRepository, times(1)).save(any());

    }


}



