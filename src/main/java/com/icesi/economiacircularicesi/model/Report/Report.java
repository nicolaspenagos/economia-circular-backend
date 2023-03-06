package com.icesi.economiacircularicesi.model.Report;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Report {

    private LocalDateTime date;
    private List<Score> reportByActivities;
    private List<Score> reportByPrinciples;



}
