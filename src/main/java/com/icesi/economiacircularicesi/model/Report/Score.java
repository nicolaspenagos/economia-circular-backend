package com.icesi.economiacircularicesi.model.Report;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Score {

    private String shortName;
    private String title;
    private double possibleScore;
    private double obtainedScore;
    private double obtainedPercentage;

}
