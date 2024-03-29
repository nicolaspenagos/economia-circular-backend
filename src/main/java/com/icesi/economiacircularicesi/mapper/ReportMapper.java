package com.icesi.economiacircularicesi.mapper;

import com.icesi.economiacircularicesi.dto.report.ReportDTO;
import com.icesi.economiacircularicesi.model.report.Report;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ReportMapper {

    Report fromDTO(ReportDTO reportDTO);

    ReportDTO fromResponse(Report report);

}
