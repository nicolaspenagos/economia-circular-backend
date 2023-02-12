package com.icesi.economiacircularicesi.repository.QuestionRepository;

import com.icesi.economiacircularicesi.model.Question.QuestionOption;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface QuestionOptionRepository extends CrudRepository<QuestionOption, UUID> {
}
