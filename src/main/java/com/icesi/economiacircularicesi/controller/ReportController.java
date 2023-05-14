package com.icesi.economiacircularicesi.controller;

import com.icesi.economiacircularicesi.api.ReportAPI;
import com.icesi.economiacircularicesi.dto.ReportDTO.ReportDTO;
import com.icesi.economiacircularicesi.mapper.ReportMapper;
import com.icesi.economiacircularicesi.service.ReportService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@AllArgsConstructor
public class ReportController implements ReportAPI {

    private ReportService reportService;
    private ReportMapper reportMapper;

    @Override
    public ReportDTO getUserReport(UUID userId, UUID responseId) {
        return reportMapper.fromResponse(reportService.getUserReport(userId, responseId));
    }

}
