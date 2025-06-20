package com.fimepay.merchantapp.settlement.service;

import com.fimepay.merchantapp.settlement.dto.ReconciliationReportDTO;
import org.springframework.core.io.Resource;
import java.time.LocalDate;
import java.util.UUID;

public interface ReconciliationService {
    ReconciliationReportDTO generateReport(UUID merchantId, LocalDate start, LocalDate end);
    Resource downloadReport(UUID reportId);
}
