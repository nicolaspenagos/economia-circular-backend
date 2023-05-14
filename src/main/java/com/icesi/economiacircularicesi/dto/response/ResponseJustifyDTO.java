package com.icesi.economiacircularicesi.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseJustifyDTO {

    private UUID id;

    @NotNull
    private UUID questionIdReference;

    private String justify;

}
