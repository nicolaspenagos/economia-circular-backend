package com.icesi.economiacircularicesi.api;

import com.icesi.economiacircularicesi.dto.PrincipleDTO.PrincipleDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.UUID;

@RequestMapping("principles")
public interface PrincipleAPI {

    @GetMapping
    public List<PrincipleDTO> getPrinciples();

    @GetMapping("/{principleId}")
    public PrincipleDTO getPrinciple(@PathVariable UUID principleId);

}
