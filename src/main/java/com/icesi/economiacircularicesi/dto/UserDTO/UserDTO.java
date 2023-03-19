package com.icesi.economiacircularicesi.dto.UserDTO;

import com.icesi.economiacircularicesi.validation.CustomAnnotation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    private UUID id;

    @NotBlank(message = "Email may not be blank")
    private String email;

    @CustomAnnotation.PasswordValidation
    private String password;

    @NotBlank(message = "Name may not be blank")
    @Size(min = 2, max = 32, message = "Name must be between 2 and 32 characters long")
    @Pattern(regexp="^[A-Za-z]*$",message = "Name can only have alphabets")
    private String name;

    @NotBlank(message = "Lastname may not be blank")
    @Size(min = 2, max = 32, message = "Lastname must be between 2 and 32 characters long")
    @Pattern(regexp="^[A-Za-z]*$",message = "Lastname can only have alphabets")
    private String lastname;

    @NotBlank(message = "Position may not be blank")
    @Pattern(regexp="^[A-Za-z]*$",message = "Position can only have alphabets")
    private String position;
    @NotBlank(message = "Sector may not be blank")
    @Pattern(regexp="^[A-Za-z]*$",message = "Sector can only have alphabets")
    private String sector;

    @NotBlank(message = "Macrosector may not be blank")
    @Pattern(regexp="^[A-Za-z]*$",message = "Macrosector can only have alphabets")
    private String macrosector;

    @NotBlank(message = "Organization may not be blank")
    @Pattern(regexp="^[A-Za-z]*$",message = "Organization can only have alphabets")
    private String organization;

    @NotNull(message = "Registration date may not be null")
    private String registrationDate;

    @NotNull
    @NotEmpty(message = "Terms and conditions history may not be empty")
    private List<TermsAndConditionsDTO> termsAndConditionsHistory;

}
