package com.icesi.economiacircularicesi.service;

import com.icesi.economiacircularicesi.dto.QuestionDTO.QuestionDTO;
import com.icesi.economiacircularicesi.model.Question.Question;

import java.util.List;

public interface QuestionService {
    public Question createQuestion(Question question);

    public List<Question> getUsers();

}
