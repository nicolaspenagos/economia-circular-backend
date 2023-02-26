package com.icesi.economiacircularicesi.mapper;

import com.icesi.economiacircularicesi.dto.ResponseDTO.ResponseDTO;
import com.icesi.economiacircularicesi.model.Response.Response;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ResponseMapper {

    Response fromDTO(ResponseDTO responseDTO);

    ResponseDTO fromResponse(Response response);


}
