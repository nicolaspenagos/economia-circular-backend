package com.icesi.economiacircularicesi.service;

import com.icesi.economiacircularicesi.dto.QuestionDTO.QuestionDTO;
import com.icesi.economiacircularicesi.model.Question.Question;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

public interface QuestionService {
    public Question createQuestion(Question question);

    public List<Question> getQuestions();

    public ResponseEntity<UUID> deleteQuestion(UUID questionId);

    public Question getQuestion(UUID questionId);

    public Question updateQuestion(UUID questionId, Question question);

}
