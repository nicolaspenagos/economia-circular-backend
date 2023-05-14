package com.icesi.economiacircularicesi.repository.response_repository;

import com.icesi.economiacircularicesi.model.response.ResponseJustify;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface ResponseJustifyRepository extends CrudRepository<ResponseJustify, UUID> {
}
