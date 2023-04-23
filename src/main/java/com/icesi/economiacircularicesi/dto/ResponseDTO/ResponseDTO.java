package com.icesi.economiacircularicesi.dto.ResponseDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseDTO {

    private UUID id;

    private LocalDateTime responseDate;

    private UUID userId;

    @NotNull
    private boolean complete;

    @NotNull
    @NotEmpty(message = "Options may not be empty")
    private List<ResponseOptionDTO> selectedOptions;

    private List<ResponseJustifyDTO> justifyList;

}
