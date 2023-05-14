package com.icesi.economiacircularicesi.repository.user_repository;

import com.icesi.economiacircularicesi.model.user.TermsAndConditions;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface TermsAndConditionsRepository extends CrudRepository<TermsAndConditions, UUID> {
}
