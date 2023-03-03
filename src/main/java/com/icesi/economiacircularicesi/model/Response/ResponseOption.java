package com.icesi.economiacircularicesi.model.Response;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.UUID;

@Data
@Table(name = "`option_responses`")
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResponseOption {

    @Id
    @Type(type = "org.hibernate.type.PostgresUUIDType")
    @Column(name = "option_response_id")
    private UUID optionResponseId;
    //TODO Extends BaseEntity
    @Column(name = "question_id_reference")
    private UUID questionIdReference;

    @Column(name = "option_id_reference")
    private UUID optionIdReference;

    @ManyToOne(targetEntity = Response.class)
    @JoinColumn(name = "response_id")
    private Response response;

    @PrePersist
    public void generateId() {
        this.optionResponseId = UUID.randomUUID();
    }

}
