package com.icesi.economiacircularicesi.controller;

import com.icesi.economiacircularicesi.api.ResponseAPI;
import com.icesi.economiacircularicesi.dto.response.ResponseDTO;
import com.icesi.economiacircularicesi.mapper.ResponseMapper;
import com.icesi.economiacircularicesi.service.ResponseService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
public class ResponseController implements ResponseAPI {

    private final ResponseMapper responseMapper;
    private final ResponseService responseService;

    @Override
    public ResponseDTO createResponse(ResponseDTO responseDTO) {
        return responseMapper.fromResponse(responseService.createResponse(responseMapper.fromDTO(responseDTO)));
    }

    @Override
    public ResponseDTO updateResponse(UUID responseId, ResponseDTO responseDTO) {
        return responseMapper.fromResponse(responseService.updateResponse(responseId, responseMapper.fromDTO(responseDTO)));
    }

    @Override
    public List<ResponseDTO> getUserResponses(UUID userId) {
        return  responseService.getUserResponses(userId).stream().map(responseMapper::fromResponse).collect(Collectors.toList());
    }

    @Override
    public List<ResponseDTO> getActiveUserResponses(UUID userId) {
        return responseService.getUserActiveResponses(userId).stream().map(responseMapper::fromResponse).collect(Collectors.toList());
    }

    @Override
    public ResponseDTO getResponse(UUID responseId) {
        return responseMapper.fromResponse(responseService.getResponse(responseId));
    }
}
