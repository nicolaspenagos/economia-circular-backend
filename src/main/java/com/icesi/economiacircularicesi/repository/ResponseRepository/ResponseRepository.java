package com.icesi.economiacircularicesi.repository.ResponseRepository;

import com.icesi.economiacircularicesi.model.Response.Response;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ResponseRepository extends CrudRepository<Response, UUID> {

    @Query(value = "SELECT * FROM responses WHERE responses.user_id = ?1", nativeQuery = true)
    Optional<List<Response>> findByUserResponses(UUID userId);

    @Query(value = "SELECT * FROM responses WHERE responses.user_id = ?1 AND NOT responses.complete", nativeQuery = true)
    Optional<List<Response>> findByUserActiveResponses(UUID userId);

}
