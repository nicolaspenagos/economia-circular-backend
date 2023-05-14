package com.icesi.economiacircularicesi.service.impl;

import com.icesi.economiacircularicesi.model.principle.Principle;
import com.icesi.economiacircularicesi.repository.principle_repository.PrincipleRepository;
import com.icesi.economiacircularicesi.service.PrincipleService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@AllArgsConstructor
@Service
public class PrincipleServiceImpl implements PrincipleService {

    private PrincipleRepository principleRepository;

    @Override
    public List<Principle> getPrinciples() {
        return StreamSupport.stream(principleRepository.findAll().spliterator(), false).collect(Collectors.toList());
    }

    @Override
    public Principle getPrinciple(UUID principleId) {
        return principleRepository.findById(principleId).orElse(null);
    }

}
