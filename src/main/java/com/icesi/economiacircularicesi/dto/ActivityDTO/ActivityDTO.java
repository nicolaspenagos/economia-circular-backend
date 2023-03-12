package com.icesi.economiacircularicesi.dto.ActivityDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ActivityDTO {

    private UUID id;

    private String description;

    private String title;

    private String name;

    private double score;

    private boolean containsDependentScoreQuestions;

}
