package com.icesi.economiacircularicesi.mapper;

import com.icesi.economiacircularicesi.dto.ResponseDTO.ResponseDTO;
import com.icesi.economiacircularicesi.model.Response.Response;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface ResponseMapper {

    Response fromDTO(ResponseDTO responseDTO);

    ResponseDTO fromResponse(Response response);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE) // The @MappingTarget annotation lets us update an existing object without writing a lot of code.
    void updateResponseFromResponse(Response updatedResponse, @MappingTarget Response response);

}
