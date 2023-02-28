package com.icesi.economiacircularicesi.service.impl;

import com.icesi.economiacircularicesi.model.Report.Report;
import com.icesi.economiacircularicesi.model.Report.Score;
import com.icesi.economiacircularicesi.service.ReportService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Service
public class ReportServiceImpl implements ReportService {

    @Override
    public Report getUserResult(UUID userId) {

        Report report = new Report();
        report.setDate(LocalDateTime.now());

        List<Score> a = new ArrayList<>();
        a.add(new Score("A1", "A1 tajsdgas", 1000.0, 800.0, 0.6));
        a.add(new Score("A2", "A2 tajsdgas", 1000.0, 800.0, 0.6));

        List<Score> p = new ArrayList<>();
        p.add(new Score("P1", "P1 tajsdgas", 1000.0, 800.0, 0.6));

        report.setReportByActivities(a);
        report.setReportByPrinciples(p);


        return report;
    }

}
