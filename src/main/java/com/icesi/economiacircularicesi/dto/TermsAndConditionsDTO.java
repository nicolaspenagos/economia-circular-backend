package com.icesi.economiacircularicesi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.UUID;



@Data
@AllArgsConstructor
@NoArgsConstructor
public class TermsAndConditionsDTO {

    private UUID termsAndConditionsId;

    private String acceptanceDate;

    private String link;

}
