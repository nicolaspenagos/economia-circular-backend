package com.icesi.economiacircularicesi.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@Table(name = "terms_and_conditions")
public class TermsAndConditions {

    @Id
    private UUID termsAndConditionsId;

    private LocalDateTime acceptanceDate;

    private String termsAndConditionsLink;

    @PrePersist
    public void generateId(){
        this.termsAndConditionsId = UUID.randomUUID();
    }
}
