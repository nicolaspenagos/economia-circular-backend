package com.icesi.economiacircularicesi.service;

import com.icesi.economiacircularicesi.model.response.Response;

import java.util.List;
import java.util.UUID;

public interface ResponseService {

    Response createResponse(Response response);

    Response updateResponse(UUID responseId, Response responseUpdate);

    List<Response> getUserResponses(UUID userId);

    List<Response> getUserActiveResponses(UUID userId);

    Response getResponse(UUID responseId);

}
