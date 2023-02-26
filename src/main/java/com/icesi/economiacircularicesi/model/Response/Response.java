package com.icesi.economiacircularicesi.model.Response;


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
@Table(name = "`responses`")
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Response {

    @Id
    @Type(type = "org.hibernate.type.PostgresUUIDType")
    @Column(name = "response_id")
    private UUID responseId;

    @Column(name="response_date")
    private LocalDateTime responseDate;

    @Column(name = "complete")
    private boolean complete;

    @OneToMany(mappedBy = "response", targetEntity = ResponseOption.class)
    private List<ResponseOption> selectedOptions;

    @PrePersist
    public void generateId() {
        this.responseId = UUID.randomUUID();
    }

}
