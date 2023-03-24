package com.icesi.economiacircularicesi.dto.ResponseDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseOptionDTO {

    @NotNull
    private UUID id;

    @NotNull
    private UUID questionIdReference;

    @NotNull
    private UUID optionIdReference;

}
