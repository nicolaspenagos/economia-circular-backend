package com.icesi.economiacircularicesi.api;

import com.icesi.economiacircularicesi.dto.ResponseDTO.ResponseDTO;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequestMapping("response")
public interface ResponseAPI {

   @PostMapping
   ResponseDTO createResponse(@RequestBody ResponseDTO responseDTO);

   @PatchMapping("{responseId}")
   ResponseDTO updateResponse(@PathVariable UUID responseId, @RequestBody ResponseDTO responseDTO);

   @GetMapping("users/{userId}")
   List<ResponseDTO> getUserResponses(@PathVariable UUID userId);

   @GetMapping("users/active/{userId}")
   List<ResponseDTO> getActiveUserResponses(@PathVariable UUID userId);

}