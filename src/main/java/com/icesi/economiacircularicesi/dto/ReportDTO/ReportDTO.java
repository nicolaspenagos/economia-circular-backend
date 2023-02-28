package com.icesi.economiacircularicesi.dto.ReportDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReportDTO {

    private LocalDateTime date;
    private List<ScoreDTO> reportByActivities;
    private List<ScoreDTO> reportByPrinciples;


}
