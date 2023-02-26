package com.icesi.economiacircularicesi.dto.ResponseDTO;

import com.icesi.economiacircularicesi.model.Response.ResponseOption;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseDTO {

    private UUID responseId;

    private LocalDateTime responseDate;

    @NotNull
    private boolean complete;

    @NotNull
    @NotEmpty(message = "Options may not be empty")
    private List<ResponseOptionDTO> selectedOptions;

}
