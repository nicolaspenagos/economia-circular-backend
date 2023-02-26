package com.icesi.economiacircularicesi.service.impl;

import com.icesi.economiacircularicesi.constant.ErrorCode;
import com.icesi.economiacircularicesi.error.exception.CustomError.CustomError;
import com.icesi.economiacircularicesi.error.exception.CustomError.CustomException;
import com.icesi.economiacircularicesi.mapper.ResponseMapper;
import com.icesi.economiacircularicesi.model.Response.Response;
import com.icesi.economiacircularicesi.model.Response.ResponseOption;
import com.icesi.economiacircularicesi.repository.ResponseRepository.ResponseOptionRepository;
import com.icesi.economiacircularicesi.repository.ResponseRepository.ResponseRepository;
import com.icesi.economiacircularicesi.service.ResponseService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Service
public class ResponseServiceImpl implements ResponseService {

    private ResponseRepository responseRepository;
    private ResponseOptionRepository responseOptionRepository;

    private ResponseMapper responseMapper;

    @Override
    public Response createResponse(Response response) {
        Response savedResponse =  responseRepository.save(response);
        saveResponseOptions(savedResponse.getResponseId(), savedResponse.getSelectedOptions());
        return  savedResponse;
    }

    private void saveResponseOptions(UUID responseId, List<ResponseOption> responseOptions){

        for(ResponseOption currentOpt:responseOptions){

            Response responseRef = new Response();
            responseRef.setResponseId(responseId);
            currentOpt.setResponse(responseRef);
            responseOptionRepository.save(currentOpt);

        }
    }

    @Override
    public Response updateResponse(UUID responseId, Response responseUpdate) {

        Response response = responseRepository.findById(responseId).orElseThrow(()-> new CustomException(HttpStatus.BAD_REQUEST, new CustomError(ErrorCode.CODE_R01_RESPONSE_NOT_FOUND, ErrorCode.CODE_R01_RESPONSE_NOT_FOUND.getMessage())));

        if(responseUpdate.getSelectedOptions()!= null && !responseUpdate.getSelectedOptions().isEmpty())
            deleteResponseRelations(response.getSelectedOptions());

        responseMapper.updateResponseFromResponse(responseUpdate, response);

        saveResponseOptions(response.getResponseId(), response.getSelectedOptions());

        return responseRepository.save(response);

    }

    private void deleteResponseRelations(List<ResponseOption> selectedOptions){
        for(ResponseOption currentOpt: selectedOptions){
            responseOptionRepository.delete(currentOpt);
        }
    }








}
