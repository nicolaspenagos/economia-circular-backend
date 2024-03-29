package com.icesi.economiacircularicesi.api;

import com.icesi.economiacircularicesi.dto.report.ReportDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.UUID;

@RequestMapping("report")
public interface ReportAPI {

    @GetMapping("/{userId}/{responseId}")
    ReportDTO getUserReport(@PathVariable UUID userId, @PathVariable UUID responseId);

}
