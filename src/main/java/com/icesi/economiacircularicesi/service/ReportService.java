package com.icesi.economiacircularicesi.service;

import com.icesi.economiacircularicesi.model.Report.Report;

import java.util.UUID;

public interface ReportService {

    Report getUserReport(UUID userId, UUID responseId);

}
