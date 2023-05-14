package com.icesi.economiacircularicesi.model.Response;


import com.icesi.economiacircularicesi.model.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


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
public class Response extends BaseEntity {

    @Column(name="response_date")
    private LocalDateTime responseDate;

    @Column(name="user_id")
    private UUID userId;

    @Column(name = "complete")
    private boolean complete;

    @OneToMany(mappedBy = "response", targetEntity = ResponseOption.class)
    private List<ResponseOption> selectedOptions;

    @OneToMany(mappedBy = "response", targetEntity = ResponseJustify.class)
    private List<ResponseJustify> justifyList;

    public Response(UUID id, LocalDateTime responseDate, UUID userId, boolean complete, List<ResponseOption> selectedOptions, List<ResponseJustify> justifyList) {
        super.setId(id);
        this.responseDate = responseDate;
        this.userId = userId;
        this.complete = complete;
        this.selectedOptions = selectedOptions;
        this.justifyList = justifyList;
    }
    @PrePersist
    public void generateId() {
        super.setId(UUID.randomUUID());
    }

}
