package com.icesi.economiacircularicesi.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Table(name = "`terms_and_conditions`")
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TermsAndConditions {

    @Id
    private UUID termsAndConditionsId;

    private LocalDateTime acceptanceDate;

    private String link;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "user_id")
    private User user;

    @PrePersist
    public void generateId(){
        this.termsAndConditionsId = UUID.randomUUID();
    }


}
