package com.icesi.economiacircularicesi.dto.report;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ScoreDTO {

    private UUID id;
    private String shortname;
    private String title;
    private double possibleScore;
    private double obtainedScore;
    private double obtainedPercentage;

}
