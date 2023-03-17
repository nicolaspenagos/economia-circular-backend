package com.icesi.economiacircularicesi.model.Report;

import com.icesi.economiacircularicesi.model.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Score extends BaseEntity {

    private String shortname;
    private String title;
    private double possibleScore;
    private double obtainedScore;
    private double obtainedPercentage;

    public Score(UUID id, String shortname, String title, double possibleScore, double obtainedScore, double obtainedPercentage) {
        super.setId(id);
        this.shortname = shortname;
        this.title = title;
        this.possibleScore = possibleScore;
        this.obtainedScore = obtainedScore;
        this.obtainedPercentage = obtainedPercentage;
    }

}
