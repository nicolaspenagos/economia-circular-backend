package com.icesi.economiacircularicesi.controller;

import com.icesi.economiacircularicesi.api.PrincipleAPI;
import com.icesi.economiacircularicesi.dto.PrincipleDTO.PrincipleDTO;
import com.icesi.economiacircularicesi.mapper.PrincipleMapper;
import com.icesi.economiacircularicesi.service.PrincipleService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
public class PrincipleController implements PrincipleAPI {

    private PrincipleMapper principleMapper;

    private PrincipleService principleService;

    @Override
    public List<PrincipleDTO> getPrinciples() {
        return principleService.getPrinciples().stream().map(principleMapper::fromPrinciple).collect(Collectors.toList());
    }

    @Override
    public PrincipleDTO getPrinciple(UUID principleId) {
        return principleMapper.fromPrinciple(principleService.getPrinciple(principleId));
    }
}
