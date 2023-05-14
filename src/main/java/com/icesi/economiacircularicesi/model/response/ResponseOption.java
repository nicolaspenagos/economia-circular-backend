package com.icesi.economiacircularicesi.model.response;


import com.icesi.economiacircularicesi.model.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

@Data
@Table(name = "`option_responses`")
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResponseOption extends BaseEntity {

    @Column(name = "question_id_reference")
    private UUID questionIdReference;

    @Column(name = "option_id_reference")
    private UUID optionIdReference;

    @ManyToOne(targetEntity = Response.class)
    @JoinColumn(name = "response_id")
    private Response response;

    public ResponseOption(UUID id,UUID questionIdReference, UUID optionIdReference, Response response) {
        super.setId(id);
        this.questionIdReference = questionIdReference;
        this.optionIdReference = optionIdReference;
        this.response = response;
    }

    @PrePersist
    public void generateId() {
        super.setId(UUID.randomUUID());
    }

}
