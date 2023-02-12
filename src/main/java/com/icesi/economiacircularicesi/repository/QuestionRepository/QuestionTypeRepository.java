package com.icesi.economiacircularicesi.repository.QuestionRepository;

import com.icesi.economiacircularicesi.model.Question.QuestionType;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface QuestionTypeRepository extends CrudRepository<QuestionType, UUID> {
}
