package com.icesi.economiacircularicesi.model.User;

import com.icesi.economiacircularicesi.model.BaseEntity;
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
public class User extends BaseEntity {

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "name")
    private String name;

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

    public User(UUID id, String email, String password, String name, String position, String sector, String macrosector, String organization, LocalDateTime registrationDate, List<TermsAndConditions> termsAndConditionsHistory) {

        super.setId(id);
        this.email = email;
        this.password = password;
        this.name = name;
        this.position = position;
        this.sector = sector;
        this.macrosector = macrosector;
        this.organization = organization;
        this.registrationDate = registrationDate;
        this.termsAndConditionsHistory = termsAndConditionsHistory;

    }
    @PrePersist
    public void generateId() {super.setId(UUID.randomUUID());}


}

