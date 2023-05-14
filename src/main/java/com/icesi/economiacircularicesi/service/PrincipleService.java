package com.icesi.economiacircularicesi.service;

import com.icesi.economiacircularicesi.model.principle.Principle;

import java.util.List;
import java.util.UUID;

public interface PrincipleService {

    List<Principle> getPrinciples();

    Principle getPrinciple(UUID principleId);

}
