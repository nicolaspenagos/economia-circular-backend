package com.icesi.economiacircularicesi.repository;

import com.icesi.economiacircularicesi.model.TermsAndConditions;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface TermsAndConditionsRepository extends CrudRepository<TermsAndConditions, UUID> {
}
