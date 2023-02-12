package com.icesi.economiacircularicesi.model.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@Table(name = "`users`")
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @Type(type = "org.hibernate.type.PostgresUUIDType")
    @Column(name = "user_id")
    private UUID userId;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "name")
    private String name;

    @Column(name = "lastname")
    private String lastname;

    @Column(name = "position")
    private String position;

    @Column(name = "sector")
    private String sector;

    @Column(name = "macrosector")
    private String macrosector;

    @Column(name = "organization")
    private String organization;

    @Column(name = "registration_date")
    private LocalDateTime registrationDate;

    @OneToMany(mappedBy = "user", targetEntity = TermsAndConditions.class)
    private List<TermsAndConditions> termsAndConditionsHistory;

    @PrePersist
    public void generateId() {this.userId = UUID.randomUUID();}



}

