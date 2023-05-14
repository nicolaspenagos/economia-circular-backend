package com.icesi.economiacircularicesi.repository.question_repository;

import com.icesi.economiacircularicesi.model.question.QuestionOption;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface QuestionOptionRepository extends CrudRepository<QuestionOption, UUID> {
}
