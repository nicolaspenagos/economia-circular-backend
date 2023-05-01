package com.icesi.economiacircularicesi.service.impl;

import com.icesi.economiacircularicesi.constant.ErrorCode;
import com.icesi.economiacircularicesi.error.exception.CustomError.CustomError;
import com.icesi.economiacircularicesi.error.exception.CustomError.CustomException;
import com.icesi.economiacircularicesi.mapper.ResponseMapper;
import com.icesi.economiacircularicesi.model.Response.Response;
import com.icesi.economiacircularicesi.model.Response.ResponseJustify;
import com.icesi.economiacircularicesi.model.Response.ResponseOption;
import com.icesi.economiacircularicesi.repository.ResponseRepository.ResponseJustifyRepository;
import com.icesi.economiacircularicesi.repository.ResponseRepository.ResponseOptionRepository;
import com.icesi.economiacircularicesi.repository.ResponseRepository.ResponseRepository;
//import com.icesi.economiacircularicesi.security.SecurityContextHolder;
//import com.icesi.economiacircularicesi.security.SecurityContextHolder;
import com.icesi.economiacircularicesi.security.SecurityContextHolder;
import com.icesi.economiacircularicesi.service.ResponseService;
import com.icesi.economiacircularicesi.service.UserService;
import com.icesi.economiacircularicesi.utils.ErrorExceptionUtils;
import lombok.AllArgsConstructor;
import org.hibernate.mapping.Array;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Service
public class ResponseServiceImpl implements ResponseService {

    private ResponseRepository responseRepository;
    private ResponseOptionRepository responseOptionRepository;

    private ResponseJustifyRepository responseJustifyRepository;
    private UserService userService;

    private ResponseMapper responseMapper;

    @Override
    public Response createResponse(Response response) {

        // We do not need to check if the user exists because in the AuthorizationFilter we are doing so (just a user that exists can be logged in)
        response.setUserId(SecurityContextHolder.getContext().getUserId());
        response.setResponseDate(LocalDateTime.now());

        Response savedResponse =  responseRepository.save(response);

        saveResponseOptions(savedResponse.getId(), savedResponse.getSelectedOptions());
        saveResponsesJustifies(savedResponse.getId(),savedResponse.getJustifyList());

        return  savedResponse;
    }

    private void saveResponseOptions(UUID responseId, List<ResponseOption> responseOptions){

        for(ResponseOption currentOpt:responseOptions){

            Response responseRef = new Response();
            responseRef.setId(responseId);
            currentOpt.setResponse(responseRef);
            responseOptionRepository.save(currentOpt);

        }
    }

    private void saveResponsesJustifies(UUID responseId, List<ResponseJustify> responseJustifies){
        for(ResponseJustify currentJustify:responseJustifies){

            Response responseRef = new Response();
            responseRef.setId(responseId);
            currentJustify.setResponse(responseRef);
            responseJustifyRepository.save(currentJustify);
        }
    }

    @Override
    public Response updateResponse(UUID responseId, Response responseUpdate) {

        Response response = responseRepository.findById(responseId).orElseThrow(()-> new CustomException(HttpStatus.BAD_REQUEST, new CustomError(ErrorCode.CODE_R02_RESPONSE_NOT_FOUND, ErrorCode.CODE_R02_RESPONSE_NOT_FOUND.getMessage())));

        if(!response.getUserId().equals(SecurityContextHolder.getContext().getUserId()))
            ErrorExceptionUtils.throwCustomException(HttpStatus.UNAUTHORIZED, ErrorCode.CODE_A04_UNAUTHORIZED);

        if((responseUpdate.getSelectedOptions()!= null && !responseUpdate.getSelectedOptions().isEmpty())&&responseUpdate.getJustifyList()!=null){
            deleteResponseRelations(response.getSelectedOptions(), response.getJustifyList());

        }

        responseMapper.updateResponseFromResponse(responseUpdate, response);

        saveResponseOptions(response.getId(), response.getSelectedOptions());
        saveResponsesJustifies(response.getId(), response.getJustifyList());

        return responseRepository.save(response);

    }

    @Override
    public List<Response> getUserResponses(UUID userId) {
        return responseRepository.findByUserResponses(userId).orElse(new ArrayList<>());
    }

    @Override
    public List<Response> getUserActiveResponses(UUID userId) {
        return responseRepository.findByUserActiveResponses(userId).orElse(new ArrayList<>());
    }

    @Override
    public Response getResponse(UUID responseId) {
        return responseRepository.findById(responseId).orElse(null);
    }

    private void deleteResponseRelations(List<ResponseOption> selectedOptions, List<ResponseJustify> justifyList){
        for(ResponseOption currentOpt: selectedOptions){

            responseOptionRepository.delete(currentOpt);
        }
        for(ResponseJustify currentJustify : justifyList){


            responseJustifyRepository.delete(currentJustify);
        }
    }








}
