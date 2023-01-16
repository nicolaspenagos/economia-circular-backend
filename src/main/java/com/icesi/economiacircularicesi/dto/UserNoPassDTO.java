package com.icesi.economiacircularicesi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserNoPassDTO {

    private UUID userId;

    private String email;

    private String name;

    private String lastname;

    private String position;

    private String sector;

    private String macrosector;

    private String organization;

    private String registrationDate;

    private List<TermsAndConditionsDTO> termsAndConditionsHistory;
}
