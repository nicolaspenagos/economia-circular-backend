package com.icesi.economiacircularicesi.mapper;

import com.icesi.economiacircularicesi.dto.principle.PrincipleDTO;
import com.icesi.economiacircularicesi.model.principle.Principle;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PrincipleMapper {

    Principle fromDTO(PrincipleDTO principleDTO);

    PrincipleDTO fromPrinciple(Principle principle);

}
