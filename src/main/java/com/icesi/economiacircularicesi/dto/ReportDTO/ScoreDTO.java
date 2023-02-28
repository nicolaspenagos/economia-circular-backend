package com.icesi.economiacircularicesi.dto.ReportDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ScoreDTO {

    private String shortName;
    private String fullName;
    private double possibleScore;
    private double obtainedScore;
    private double obtainedPercentage;

}
