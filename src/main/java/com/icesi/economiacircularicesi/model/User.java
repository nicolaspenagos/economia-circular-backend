package com.icesi.economiacircularicesi.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@Table(name = "`user`")
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    private UUID userId;

    private String email;
    private String name;
    private String lastname;
    private String position;
    private String sector;
    private String macrosector;
    private LocalDateTime registrationDate;

    /*
    @OneToMany(mappedBy = "user", targetEntity = TermsAndConditions.class)
    private List<TermsAndConditions> termsAndConditionsHistory;
    */


    @PrePersist
    public void generateId(){
        this.userId = UUID.randomUUID();
    }
}

