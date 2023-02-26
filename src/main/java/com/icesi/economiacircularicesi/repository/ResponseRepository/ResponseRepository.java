package com.icesi.economiacircularicesi.repository.ResponseRepository;

import com.icesi.economiacircularicesi.model.Response.Response;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface ResponseRepository extends CrudRepository<Response, UUID> {
}
