package com.icesi.economiacircularicesi.model.Response;

import com.icesi.economiacircularicesi.model.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

@Data
@Table(name = "`justify_responses`")
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResponseJustify extends BaseEntity {

    @Column(name = "question_id_reference")
    private UUID questionIdReference;

    @Column(name = "justify")
    private String justify;

    @ManyToOne(targetEntity = Response.class)
    @JoinColumn(name = "response_id")
    private Response response;

    public ResponseJustify(UUID id,UUID questionIdReference, String justify){
        super.setId(id);
        this.questionIdReference = questionIdReference;
        this.justify = justify;
    }
    @PrePersist
    public void generateId() {
        super.setId(UUID.randomUUID());
    }
}
