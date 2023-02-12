package com.icesi.economiacircularicesi.controller;


import com.icesi.economiacircularicesi.api.QuestionAPI;
import com.icesi.economiacircularicesi.dto.QuestionDTO.QuestionDTO;
import com.icesi.economiacircularicesi.mapper.QuestionMapper;
import com.icesi.economiacircularicesi.service.QuestionService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
public class QuestionController implements QuestionAPI {

    public final QuestionService questionService;
    public final QuestionMapper questionMapper;

    @Override
    public QuestionDTO createQuestion(@Valid QuestionDTO questionDTO) {
        return questionMapper.fromQuestion(questionService.createQuestion(questionMapper.fromDTO(questionDTO)));
    }

    @Override
    public List<QuestionDTO> getQuestions() {
        return questionService.getUsers().stream().map(questionMapper::fromQuestion).collect(Collectors.toList());
    }

}

