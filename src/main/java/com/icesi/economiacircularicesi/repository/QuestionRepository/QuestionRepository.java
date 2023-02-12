package com.icesi.economiacircularicesi.repository.QuestionRepository;

import com.icesi.economiacircularicesi.model.Question.Question;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface QuestionRepository extends CrudRepository<Question, UUID> {
}
