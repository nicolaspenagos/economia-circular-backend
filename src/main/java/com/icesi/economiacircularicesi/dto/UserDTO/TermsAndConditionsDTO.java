package com.icesi.economiacircularicesi.dto.UserDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;



@Data
@AllArgsConstructor
@NoArgsConstructor
public class TermsAndConditionsDTO {

    private UUID termsAndConditionsId;

    @NotBlank(message = "Link may not be blank")
    private String acceptanceDate;

    @NotNull(message = "Registration date may not be null")
    private String link;

}
