package com.icesi.economiacircularicesi.repository.principle_repository;

import com.icesi.economiacircularicesi.model.principle.Principle;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface PrincipleRepository extends CrudRepository<Principle, UUID> {
}
