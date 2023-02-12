package com.icesi.economiacircularicesi.repository.UserRepository;

import com.icesi.economiacircularicesi.model.User.TermsAndConditions;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface TermsAndConditionsRepository extends CrudRepository<TermsAndConditions, UUID> {
}
