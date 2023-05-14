package com.icesi.economiacircularicesi.api;

import com.icesi.economiacircularicesi.dto.question.QuestionDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequestMapping("questions")
public interface QuestionAPI {
    @PostMapping
    QuestionDTO createQuestion(@RequestBody QuestionDTO questionDTO);

    @GetMapping
    List<QuestionDTO> getQuestions();

    @DeleteMapping("/{questionId}")
    ResponseEntity<UUID> deleteQuestion(@PathVariable UUID questionId);

    @GetMapping("/{questionId}")
    QuestionDTO getQuestion(@PathVariable UUID questionId);

    @PatchMapping("/{questionId}")
    QuestionDTO updateQuestion(@PathVariable UUID questionId, @RequestBody QuestionDTO questionDTO);




}

