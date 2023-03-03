package com.icesi.economiacircularicesi.model.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

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
    @Type(type = "org.hibernate.type.PostgresUUIDType")
    private UUID termsAndConditionsId;
    //TODO Extends BaseEntity
    @Column(name = "acceptance_date")
    private LocalDateTime acceptanceDate;

    @Column(name = "link")
    private String link;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "user_id")
    private User user;

    @PrePersist
    public void generateId(){
        this.termsAndConditionsId = UUID.randomUUID();
    }


}
