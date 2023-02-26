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


}
