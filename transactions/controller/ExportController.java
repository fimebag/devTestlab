package com.fimepay.merchantapp.transactions.controller;

import com.fimepay.merchantapp.transactions.dto.*;
import com.fimepay.merchantapp.transactions.service.ExportService;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/merchant/{merchantId}/transactions/export")
public class ExportController {

    private final ExportService svc;

    public ExportController(ExportService svc) {
        this.svc = svc;
    }

    @PostMapping
    public ResponseEntity<TransactionExportJobDTO> schedule(
            @PathVariable UUID merchantId,
            @Valid @RequestBody CreateExportJobRequest req) {
        return ResponseEntity.ok(svc.scheduleExport(merchantId, req));
    }

    @GetMapping("/{jobId}/run")
    public ResponseEntity<byte[]> runNow(@PathVariable UUID jobId) {
        byte[] csv = svc.runExportNow(jobId);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.TEXT_PLAIN);
        headers.setContentDisposition(ContentDisposition.attachment().filename("export.csv").build());
        return new ResponseEntity<>(csv, headers, HttpStatus.OK);
    }
}

