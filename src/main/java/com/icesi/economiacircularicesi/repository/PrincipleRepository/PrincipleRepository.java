package com.icesi.economiacircularicesi.repository.PrincipleRepository;

import com.icesi.economiacircularicesi.model.Principle.Principle;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface PrincipleRepository extends CrudRepository<Principle, UUID> {
}
