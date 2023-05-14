package com.icesi.economiacircularicesi.model.user;

import com.icesi.economiacircularicesi.model.BaseEntity;
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
public class TermsAndConditions extends BaseEntity {

    @Column(name = "acceptance_date")
    private LocalDateTime acceptanceDate;

    @Column(name = "link")
    private String link;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "user_id")
    private User user;

    public TermsAndConditions(UUID id, LocalDateTime acceptanceDate, String link, User user) {
        super.setId(id);
        this.acceptanceDate = acceptanceDate;
        this.link = link;
        this.user = user;
    }

    @PrePersist
    public void generateId(){
        super.setId(UUID.randomUUID());
    }


}
