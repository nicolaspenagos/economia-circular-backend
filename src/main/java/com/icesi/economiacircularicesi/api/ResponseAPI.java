package com.icesi.economiacircularicesi.api;

import com.icesi.economiacircularicesi.dto.ResponseDTO.ResponseDTO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("response")
public interface ResponseAPI {

   @PostMapping
   ResponseDTO createResponse(@RequestBody ResponseDTO responseDTO);


}
