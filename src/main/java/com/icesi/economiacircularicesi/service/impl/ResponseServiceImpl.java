package com.icesi.economiacircularicesi.service.impl;

import com.icesi.economiacircularicesi.model.Question.Question;
import com.icesi.economiacircularicesi.model.Question.QuestionOption;
import com.icesi.economiacircularicesi.model.Response.Response;
import com.icesi.economiacircularicesi.model.Response.ResponseOption;
import com.icesi.economiacircularicesi.repository.ResponseRepository.ResponseOptionRepository;
import com.icesi.economiacircularicesi.repository.ResponseRepository.ResponseRepository;
import com.icesi.economiacircularicesi.service.ResponseService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Service
public class ResponseServiceImpl implements ResponseService {

    private ResponseRepository responseRepository;
    private ResponseOptionRepository responseOptionRepository;

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





}
