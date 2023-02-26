package com.icesi.economiacircularicesi.repository.ResponseRepository;

import com.icesi.economiacircularicesi.model.Response.ResponseOption;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface ResponseOptionRepository extends CrudRepository<ResponseOption, UUID> {
}
