package com.icesi.economiacircularicesi.dto.PrincipleDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PrincipleDTO {

    private UUID principleId;

    private String description;

    private String title;

    private String name;

    private double score;


}
