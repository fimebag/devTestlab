package com.fimepay.merchantapp.settlement.controller;

import com.fimepay.merchantapp.settlement.dto.ReconciliationReportDTO;
import com.fimepay.merchantapp.settlement.service.ReconciliationService;
import org.springframework.core.io.Resource;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.UUID;

@RestController
@RequestMapping("/merchant/{merchantId}")
public class ReconciliationController {

    private final ReconciliationService svc;

    public ReconciliationController(ReconciliationService svc) {
        this.svc = svc;
    }

    @PostMapping("/reconciliation")
    public ResponseEntity<ReconciliationReportDTO> generate(
            @PathVariable UUID merchantId,
            @RequestParam @NotNull LocalDate periodStart,
            @RequestParam @NotNull LocalDate periodEnd) {
        return ResponseEntity.ok(svc.generateReport(merchantId, periodStart, periodEnd));
    }

    @GetMapping("/reconciliation/{reportId}/download")
    public ResponseEntity<Resource> download(@PathVariable UUID reportId) {
        Resource r = svc.downloadReport(reportId);
        return ResponseEntity.ok()
                             .contentType(MediaType.parseMediaType("text/csv"))
                             .header(HttpHeaders.CONTENT_DISPOSITION,
                                     "attachment; filename=\"reconciliation.csv\"")
                             .body(r);
    }
}
