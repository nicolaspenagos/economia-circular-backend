package com.icesi.economiacircularicesi.controller;


import com.icesi.economiacircularicesi.api.QuestionAPI;
import com.icesi.economiacircularicesi.dto.QuestionDTO.QuestionDTO;
import com.icesi.economiacircularicesi.mapper.QuestionMapper;
import com.icesi.economiacircularicesi.service.QuestionService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
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
        System.out.println(questionDTO);
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


}

