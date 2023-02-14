package com.icesi.economiacircularicesi.api;

import com.icesi.economiacircularicesi.dto.QuestionDTO.QuestionDTO;
import com.icesi.economiacircularicesi.dto.UserDTO.UserDTO;
import com.icesi.economiacircularicesi.dto.UserDTO.UserNoPassDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequestMapping("questions")
public interface QuestionAPI {
    @PostMapping
    public QuestionDTO createQuestion(@RequestBody QuestionDTO questionDTO);

    @GetMapping
    public List<QuestionDTO> getQuestions();

    @DeleteMapping("/{questionId}")
    public ResponseEntity<UUID> deleteQuestion(@PathVariable UUID questionId);

    @GetMapping("/{questionId}")
    QuestionDTO getQuestion(@PathVariable UUID questionId);

    @PatchMapping("/{questionId}")
    public QuestionDTO updateQuestion(@PathVariable UUID questionId, @RequestBody QuestionDTO questionDTO);


}

