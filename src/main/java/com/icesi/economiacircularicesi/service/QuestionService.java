package com.icesi.economiacircularicesi.service;

import com.icesi.economiacircularicesi.model.question.Question;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

public interface QuestionService {
    Question createQuestion(Question question);

    List<Question> getQuestions();

    ResponseEntity<UUID> deleteQuestion(UUID questionId);

    Question getQuestion(UUID questionId);

    Question updateQuestion(UUID questionId, Question question);

    List<Question> getQuestionsByActivity(String activity);

}
