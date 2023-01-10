package com.icesi.economiacircularicesi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {


    private UUID userId;

    @NotBlank(message = "Email may not be blank")
    private String email;

    @NotBlank(message = "Name may not be blank")
    @Size(min = 2, max = 32, message = "Name must be between 2 and 32 characters long")
    private String name;

    @NotBlank(message = "Lastname may not be blank")
    @Size(min = 2, max = 32, message = "Name must be between 2 and 32 characters long")
    private String lastname;

    @NotBlank(message = "Position may not be blank")
    private String position;
    @NotBlank(message = "Sector may not be blank")
    private String sector;

    @NotBlank(message = "Macrosector may not be blank")
    private String macrosector;

    @NotNull(message = "Registration date may not be null")
    private String registrationDate;

    @NotNull(message = "Terms and conditions history may not be empty")
    @NotEmpty
    private List<TermsAndConditionsDTO> termsAndConditionsHistory;

}
