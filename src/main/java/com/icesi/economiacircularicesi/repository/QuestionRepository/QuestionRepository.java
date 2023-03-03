package com.icesi.economiacircularicesi.repository.QuestionRepository;

import com.icesi.economiacircularicesi.model.Question.Question;
import com.icesi.economiacircularicesi.model.Response.Response;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface QuestionRepository extends CrudRepository<Question, UUID> {

    @Query(value = "SELECT * FROM questions WHERE questions.activity = ?1", nativeQuery = true)
    Optional<List<Question>> findByActivity(String activity);


}
