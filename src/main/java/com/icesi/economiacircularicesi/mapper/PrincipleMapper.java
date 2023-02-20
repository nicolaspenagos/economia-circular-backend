package com.icesi.economiacircularicesi.mapper;

import com.icesi.economiacircularicesi.dto.PrincipleDTO.PrincipleDTO;
import com.icesi.economiacircularicesi.model.Principle.Principle;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PrincipleMapper {

    Principle fromDTO(PrincipleDTO principleDTO);

    PrincipleDTO fromPrinciple(Principle principle);

}
