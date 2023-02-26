package com.icesi.economiacircularicesi.controller;

import com.icesi.economiacircularicesi.api.ResponseAPI;
import com.icesi.economiacircularicesi.dto.ResponseDTO.ResponseDTO;
import com.icesi.economiacircularicesi.mapper.ResponseMapper;
import com.icesi.economiacircularicesi.service.ResponseService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@AllArgsConstructor
public class ResponseController implements ResponseAPI {

    public final ResponseMapper responseMapper;
    public final ResponseService responseService;

    @Override
    public ResponseDTO createResponse(ResponseDTO responseDTO) {
        return responseMapper.fromResponse(responseService.createResponse(responseMapper.fromDTO(responseDTO)));
    }

    @Override
    public ResponseDTO updateResponse(UUID responseId, ResponseDTO responseDTO) {
        return responseMapper.fromResponse(responseService.updateResponse(responseId, responseMapper.fromDTO(responseDTO)));
        //    return questionMapper.fromQuestion(questionService.updateQuestion(questionId, questionMapper.fromDTO(questionDTO)));
    }
}
