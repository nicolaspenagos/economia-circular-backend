package com.icesi.economiacircularicesi.controller;


import com.icesi.economiacircularicesi.api.QuestionAPI;
import com.icesi.economiacircularicesi.constant.ErrorCode;
import com.icesi.economiacircularicesi.dto.QuestionDTO.QuestionDTO;
import com.icesi.economiacircularicesi.dto.QuestionDTO.QuestionOptionDTO;
import com.icesi.economiacircularicesi.mapper.QuestionMapper;
import com.icesi.economiacircularicesi.model.Question.QuestionType;
import com.icesi.economiacircularicesi.service.QuestionService;
import com.icesi.economiacircularicesi.utils.ErrorExceptionUtils;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
public class QuestionController implements QuestionAPI {

    public final QuestionService questionService;
    public final QuestionMapper questionMapper;

    @Override
    public QuestionDTO createQuestion(@Valid QuestionDTO questionDTO) {

        validateQuestionType(questionDTO.getType(), questionDTO.getQuestionOptions());
        return questionMapper.fromQuestion(questionService.createQuestion(questionMapper.fromDTO(questionDTO)));
    }

    @Override
    public List<QuestionDTO> getQuestions() {
        return questionService.getQuestions().stream().map(questionMapper::fromQuestion).collect(Collectors.toList());
    }

    @Override
    public ResponseEntity<UUID> deleteQuestion(UUID questionId) {
        return questionService.deleteQuestion(questionId);
    }

    @Override
    public QuestionDTO getQuestion(UUID questionId) {
        return questionMapper.fromQuestion(questionService.getQuestion(questionId));
    }

    @Override
    public QuestionDTO updateQuestion(UUID questionId, QuestionDTO questionDTO) {
        return questionMapper.fromQuestion(questionService.updateQuestion(questionId, questionMapper.fromDTO(questionDTO)));
    }

    private void validateQuestionType(QuestionType questionType, List<QuestionOptionDTO> questionOptions){

        Collections.sort(questionOptions, Comparator.comparingInt(QuestionOptionDTO::getOptionOrder));
        int i = 0;

        if(questionType.equals(QuestionType.MULTIPLE_CHOICE) || questionType.equals(QuestionType.SINGLE_CHOICE)){
            // Checking if first option is exclusive
            if(!questionOptions.get(0).isExclusive())
               ErrorExceptionUtils.throwCustomException(HttpStatus.BAD_REQUEST, ErrorCode.CODE_Q02_INVALID_QUESTION_OPTIONS);

            i=1;
        }

        for (; i<questionOptions.size(); i++){
            if(questionOptions.get(i).isExclusive())
                ErrorExceptionUtils.throwCustomException(HttpStatus.BAD_REQUEST, ErrorCode.CODE_Q02_INVALID_QUESTION_OPTIONS);
        }

    }


}

