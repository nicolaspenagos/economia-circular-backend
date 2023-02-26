package com.icesi.economiacircularicesi.service;

import com.icesi.economiacircularicesi.model.Response.Response;

import java.util.UUID;

public interface ResponseService {

    Response createResponse(Response response);

    Response updateResponse(UUID responseId, Response responseUpdate);

}
